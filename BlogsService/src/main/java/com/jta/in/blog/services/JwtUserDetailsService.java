package com.jta.in.blog.services;


import com.jta.in.blog.exception.UserException;
import com.jta.in.blog.model.UserModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private SqlSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userByName = this.session.selectOne("User.getByUsername", username);

        if (userByName == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        System.out.println("found: user " + userByName);

        return new org.springframework.security.core.userdetails.User(userByName.getUsername(), userByName.getPassword(),
                new ArrayList<>());
    }

    public UserModel save(UserModel user) {
        UserModel existingUser = this.session.selectOne("User.getByUsername", user.getUsername());

        if (existingUser != null) {
            System.out.println("found: " + existingUser.getUsername());
            throw new UserException("User already exists!");
        }
        UserModel newUser = new UserModel();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        int insert = this.session.insert("User.insert", newUser);
        System.out.println("inserted: " + insert);


        List<UserModel> users = this.session.selectList("User.getAll");

        for (int i = 0; i < users.size(); i++) {
            System.out.println("i :" + i + " user: " + users.get(i));
        }
        return user;
    }

}