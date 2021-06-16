package com.jta.in.blog;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSqlSession() {
        System.out.println("----- Get request ----");
        return "<----> Hello from client blog <---->";

    }
}
