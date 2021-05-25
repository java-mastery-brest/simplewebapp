package com.mastery.java.task.error_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String error;
    private List<String> errors;

    public ApiError(HttpStatus status,
                   String error,
                    String errorAsList) {
        this.status = status;
        this.error = error;
        this.errors = Collections.singletonList(errorAsList);
    }
}