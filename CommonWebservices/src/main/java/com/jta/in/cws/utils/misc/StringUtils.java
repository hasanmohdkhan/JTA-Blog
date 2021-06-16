package com.jta.in.cws.utils.misc;

public class StringUtils {

    public static String getTokenFromHeader(String token){
        return token.replace("Bearer ", "");
    }
}
