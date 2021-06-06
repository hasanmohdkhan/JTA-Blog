package com.jta.in.cws.controllers;


import com.jta.in.cws.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SqlSession sqlSession;


    @GetMapping("/home")
    public void getSqlSession() {
        int result = this.sqlSession.insert("createNewTableIfNotExists");
        logger.info("createNewTableIfNotExists: {}", result);


        Student student = new Student(0,"Test", "MC", "Test@gmail.com",80, 8909);
        int insert = this.sqlSession.insert("Student.insert", student);
        logger.info("inser: {}",insert);

        List<Student> list = this.sqlSession.selectList("Student.getAll");
        for (Student value : list) {
            logger.info("DATA: {}", value);
        }
    }
}
