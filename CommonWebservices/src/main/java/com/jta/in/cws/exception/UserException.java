package com.jta.in.cws.exception;

import org.springframework.security.core.AuthenticationException;

public class UserException  extends AuthenticationException {

    public UserException(String msg) {
        super(msg);
    }
}
