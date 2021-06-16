package com.jta.in.cws.constants;

public class ApplicationConstants {
    private ApplicationConstants() {
    }

    //Error messages
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String ID_REQUIRED = "id is required.";
    public static final String TITLE_IS_EMPTY = "Title is empty.";
    public static final String TITLE_IS_MISSING = "Title is missing.";
    public static final String BODY_IS_EMPTY = "Body is empty.";
    public static final String BODY_IS_MISSING = "Body is missing.";

    //DELETE
    public static final int DELETE_SUCCESSFUL = 1;
    public static final int DELETE_FAIL = 0;
    public static final String DELETE_SUCCESSFUL_MSG = "Deleted.";
    public static final String DELETE_FAIL_MSG = "Unable to delete requested item.";
    public static final int UPDATE_SUCCESSFUL = 1;
    public static final String UPDATE_FAIL = "Update failed!";


    //Json keys
    public static final String TITLE = "title";
    public static final String BODY = "body";

    //keys Blog
    public static final String BLOG = "Blog.";
    public static final String CREATE_BLOG_TABLE_IF_NOT_EXISTS = "createBlogTableIfNotExists";
    public static final String GET_BY_AUTHOR = "getByAuthor";
    public static final String BLOG_INSERT = "insert";
    public static final String DELETE_BY_ID_AND_USERNAME = "deleteByIdAndUserName";
    public static final String GET_BY_ID = "getById";
    public static final String UPDATE_BODY_AND_TITLE = "updateBodyAndTitle";
    public static final String UPDATE_TITLE = "updateTitle";
    public static final String UPDATE_BODY = "updateBody";

    //mode for blog
    public static final int UPDATE_MODE = 1;
    public static final int ADD_MODE = 0;
    public static final int FOUND_KEY_TITLE = 100;
    public static final int FOUND_KEY_BODY = 101;
    public static final int FOUND_KEY_BODY_TITLE = 102;
}
