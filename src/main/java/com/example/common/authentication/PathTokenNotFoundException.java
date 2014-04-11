package com.example.common.authentication;

import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * User: magnus.smith
 * Date: 11/04/2014
 * Time: 11:18
 *
 */
public class PathTokenNotFoundException extends AuthenticationException {

    public PathTokenNotFoundException(String msg) {
        super(msg);
    }

    public PathTokenNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
