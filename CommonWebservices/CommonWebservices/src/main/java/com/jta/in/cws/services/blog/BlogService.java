package com.jta.in.cws.services.blog;

import com.jta.in.cws.model.Blog;
import org.json.JSONObject;

import java.util.List;

public interface BlogService {

   List<Blog> add(String token, JSONObject jsonObject, String username) throws Exception;

   int delete(String username,String id) throws Exception;

   int update(String username,String id,JSONObject jsonObject,int keyCode) throws Exception;

}
