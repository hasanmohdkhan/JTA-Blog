package com.jta.in.cws.controllers;

import java.util.Objects;


import com.jta.in.cws.constants.EnumConstants;
import com.jta.in.cws.config.JwtTokenUtil;
import com.jta.in.cws.exception.UserException;
import com.jta.in.cws.model.JwtRequest;
import com.jta.in.cws.model.JwtResponse;
import com.jta.in.cws.model.UserModel;
import com.jta.in.cws.services.JwtUserDetailsService;
import com.jta.in.cws.utils.model.CustomResponse;
import com.jta.in.cws.utils.model.Message;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.jta.in.cws.constants.ApplicationConstants.*;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private SqlSession session;

    static int count = 0;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        CustomResponse response = new CustomResponse();

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        }catch (Exception e){
            response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
            response.setResponse(e.getMessage());
            Message message = new Message();
            message.text(e.getMessage());
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
        response.setResponse(new JwtResponse(token));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> saveUser(@RequestBody UserModel user) {
        UserModel saved = null;
        CustomResponse response = new CustomResponse();
        if (count == 0) {
            int result = this.session.insert("createUserTableIfNotExists");
            System.out.println("User table : {}" + result);
            count++;
        }

        try {
            saved = userDetailsService.save(user);
        } catch (UserException e) {
            response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
            response.setResponse(e.getMessage());
            Message message = new Message();
            message.text(e.getMessage());
            response.setMessage(message);
            System.out.println("response: " + response.toString());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
        response.setResponse(saved);
        System.out.println("response: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {

            throw new Exception(INVALID_CREDENTIALS, e);

        }
    }
}
