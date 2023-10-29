package com.enuma.blogSpot.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BlogAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
