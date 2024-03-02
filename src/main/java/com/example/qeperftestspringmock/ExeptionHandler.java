package com.example.qeperftestspringmock;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExeptionHandler {

    @ExceptionHandler({RequestNotPermitted.class})
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }

}
