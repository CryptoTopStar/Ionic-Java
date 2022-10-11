package com.smart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException  extends RuntimeException{

	public AuthException(String exception) {
        super(exception);
    }
	
	public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
