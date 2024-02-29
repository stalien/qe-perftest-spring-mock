package com.example.qeperftestspringmock.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MockController {
    @PostMapping("/mock")
    public Map<String, String> handlePostRequest(@RequestBody Map<String, String> request) {
        // просто добавляем данные из входящего запроса в ответный JSON
        String value1 = request.get("key1");
        return Map.of("key3", value1, "key4", "value3", "key5", "value4");
    }
}
