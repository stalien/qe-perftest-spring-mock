package com.example.qeperftestspringmock.controllers;

import com.example.qeperftestspringmock.models.Request;
import com.example.qeperftestspringmock.models.Response;
import com.example.qeperftestspringmock.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired
    private MockService mockService;

    @PostMapping("/mock")
    public ResponseEntity<?> handlePostRequest(@RequestBody Request request) throws InterruptedException {
        logger.info("Request: {}", request);

        Response response = mockService.generateResponse(request);
        logger.info("Response: {}", response);

        if (mockService.isError()) {
            logger.error("Status: {}", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(Map.of("error", "Произошла ошибка на сервере"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            logger.info("Status: {}", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
