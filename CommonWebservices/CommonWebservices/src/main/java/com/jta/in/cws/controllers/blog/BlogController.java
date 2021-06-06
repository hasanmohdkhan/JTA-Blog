package com.jta.in.cws.controllers.blog;

import com.jta.in.cws.config.JwtTokenUtil;
import com.jta.in.cws.constants.EnumConstants;
import com.jta.in.cws.model.Blog;
import com.jta.in.cws.services.blog.BlogService;
import com.jta.in.cws.utils.model.CustomResponse;
import com.jta.in.cws.utils.model.Message;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jta.in.cws.constants.ApplicationConstants.*;
import static com.jta.in.cws.utils.misc.StringUtils.getTokenFromHeader;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private BlogService blogService;
    @Autowired
    private SqlSession sqlSession;


    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> addBlog(@RequestBody String blogRequest, @RequestHeader(name = "Authorization") String token)
            throws Exception {
        JSONObject jsonObject;
        CustomResponse response = new CustomResponse();
        System.out.println("request: " + blogRequest);
        jsonObject = new JSONObject(blogRequest);

        try {
            validateRequestPayload(jsonObject, ADD_MODE);
            List<Blog> blogsList = blogService.add(token, jsonObject, tokenUtil.getUsernameFromToken(getTokenFromHeader(token)));
            response.setResponse(blogsList);
            response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (JSONException e) {
            response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
            response.setResponse(e.getMessage());
            Message message = new Message();
            message.text(e.getMessage());
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }


    @GetMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable String id, @RequestHeader(name = "Authorization") String token) {
        CustomResponse response = new CustomResponse();
        response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
        response.setResponse("ID: " + id);
        try {
            int delete = blogService.delete(tokenUtil.getUsernameFromToken(getTokenFromHeader(token)), id);

            if (delete == DELETE_SUCCESSFUL) {
                response.setResponse(DELETE_SUCCESSFUL_MSG);
                response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
                response.setResponse("Blog id: " + id + " " + DELETE_FAIL_MSG);
                Message message = new Message();
                message.text("Blog id: " + id + " " + DELETE_FAIL_MSG);
                response.setMessage(message);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
            response.setResponse(e.getMessage());
            Message message = new Message();
            message.text(e.getMessage());
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> updateBlog(@PathVariable String id, @RequestBody String blogRequest, @RequestHeader(name = "Authorization") String token) throws Exception {
        JSONObject jsonObject;
        CustomResponse response = new CustomResponse();
        System.out.println("request: " + blogRequest);
        jsonObject = new JSONObject(blogRequest);

        try {
            int keyCode = validateRequestPayload(jsonObject, UPDATE_MODE);
            //id json username
            int update = blogService.update(tokenUtil.getUsernameFromToken(getTokenFromHeader(token)), id, jsonObject, keyCode);
            if (update == UPDATE_SUCCESSFUL) {
                Blog blog = new Blog();
                blog.setId(Integer.valueOf(id));
                blog.setAuthor(tokenUtil.getUsernameFromToken(getTokenFromHeader(token)));
                Blog updatedBlog = this.sqlSession.selectOne(BLOG + GET_BY_ID, blog);
                response.setResponse(updatedBlog);
                response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
                response.setResponse(UPDATE_FAIL);
                Message message = new Message();
                message.text(UPDATE_FAIL);
                response.setMessage(message);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }


        } catch (JSONException e) {
            response.setResponseStatus(EnumConstants.ResponseStatus.FAILURE.getValue());
            response.setResponse(e.getMessage());
            Message message = new Message();
            message.text(e.getMessage());
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }


    private int validateRequestPayload(JSONObject blogRequest, int mode) throws JSONException {
        if (mode == ADD_MODE) {
            validatedKeys(blogRequest, TITLE, TITLE_IS_EMPTY, TITLE_IS_MISSING);
            validatedKeys(blogRequest, BODY, BODY_IS_EMPTY, BODY_IS_EMPTY);
            return 0;
        }

        if (blogRequest.has(TITLE) && blogRequest.has(BODY)) {
            validatedKeys(blogRequest, TITLE, TITLE_IS_EMPTY, TITLE_IS_MISSING);
            validatedKeys(blogRequest, BODY, BODY_IS_EMPTY, BODY_IS_MISSING);
            return FOUND_KEY_BODY_TITLE;
        }

        if (blogRequest.has(TITLE)) {
            validatedKeys(blogRequest, TITLE, TITLE_IS_EMPTY, TITLE_IS_MISSING);
            return FOUND_KEY_TITLE;
        }
        if (blogRequest.has(BODY)) {
            validatedKeys(blogRequest, BODY, BODY_IS_EMPTY, BODY_IS_MISSING);
            return FOUND_KEY_BODY;
        }
        return 0;
    }

    private void validatedKeys(JSONObject blogRequest, String key, String emptyLabel, String missingLabel) throws JSONException {
        boolean isTitle = blogRequest.has(key);
        if (isTitle) {
            if (blogRequest.getString(key).isEmpty()) {
                throw new JSONException(emptyLabel);
            }
        } else {
            throw new JSONException(missingLabel);
        }
    }


}
