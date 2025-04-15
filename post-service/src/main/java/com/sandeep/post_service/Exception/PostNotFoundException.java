package com.sandeep.post_service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String postNotFound) {
        super(postNotFound);
    }
}
