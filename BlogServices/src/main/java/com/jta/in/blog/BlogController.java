package com.jta.in.blog;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {
    Logger logger = LoggerFactory.getLogger(BlogController.class);


    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSqlSession() {

        return "<----> Hello from client blog <---->";


    }
}
