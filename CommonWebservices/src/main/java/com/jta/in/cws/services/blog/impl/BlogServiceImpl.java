package com.jta.in.cws.services.blog.impl;

import com.jta.in.cws.constants.ApplicationConstants;
import com.jta.in.cws.model.Blog;
import com.jta.in.cws.services.blog.BlogService;
import com.jta.in.cws.utils.time.TimeStampUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jta.in.cws.constants.ApplicationConstants.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Blog> add(String token, JSONObject jsonObject, String username) throws Exception {
        Blog blog = new Blog();
        blog.setTitle(jsonObject.getString(ApplicationConstants.TITLE));
        blog.setBody(jsonObject.getString(ApplicationConstants.BODY));
        blog.setAuthor(username);
        blog.setCreatedAt(TimeStampUtils.getCurrentTimestampInUTC());
        blog.setUpdatedAt(null);

        this.sqlSession.insert(ApplicationConstants.CREATE_BLOG_TABLE_IF_NOT_EXISTS);
        this.sqlSession.insert(BLOG + BLOG_INSERT, blog);
        List<Blog> blogsList = this.sqlSession.selectList(BLOG + GET_BY_AUTHOR, username);

        /*System.out.println("token: " + token);*/
        for (Blog b : blogsList) {
            System.out.println("blog: " + b);
        }
        return blogsList;
    }

    @Override
    public int delete(String username, String id) throws Exception {
        Blog blog = new Blog();
        blog.setId(Integer.valueOf(id));
        blog.setAuthor(username);
        this.sqlSession.insert(CREATE_BLOG_TABLE_IF_NOT_EXISTS);
        int delete = this.sqlSession.delete(BLOG + DELETE_BY_ID_AND_USERNAME, blog);
        System.out.println("Deleted: " + delete);
        return delete;
    }

    @Override
    public int update(String username, String id, JSONObject jsonObject, int keyCode) throws Exception {
        Blog blog = new Blog();
        int update = 0;
        switch (keyCode){
            case FOUND_KEY_BODY:{
                blog.setAuthor(username);
                blog.setUpdatedAt(TimeStampUtils.getCurrentTimestampInUTC());
                blog.setBody(jsonObject.getString(BODY));
                blog.setId(Integer.valueOf(id));
                update = this.sqlSession.update(BLOG + UPDATE_BODY, blog);
                System.out.println("Updated body:" +update);
            }
            break;
            case FOUND_KEY_TITLE:{
                blog.setTitle(jsonObject.getString(TITLE));
                blog.setAuthor(username);
                blog.setUpdatedAt(TimeStampUtils.getCurrentTimestampInUTC());
                blog.setId(Integer.valueOf(id));
                update = this.sqlSession.update(BLOG + UPDATE_TITLE, blog);
                System.out.println("Updated title :" +update);
            }
            break;
            case FOUND_KEY_BODY_TITLE:{
                blog.setTitle(jsonObject.getString(TITLE));
                blog.setAuthor(username);
                blog.setBody(jsonObject.getString(BODY));
                blog.setUpdatedAt(TimeStampUtils.getCurrentTimestampInUTC());
                blog.setId(Integer.valueOf(id));
                update = this.sqlSession.update(BLOG + UPDATE_BODY_AND_TITLE, blog);
                System.out.println("Updated body & title :" +update);
            }
            break;
            default:
                System.out.println("no key found.");
        }

        return update;
    }


}
