package com.example.qeperftestspringmock.controllers;

import com.example.qeperftestspringmock.kafka.KafkaProducer;
import com.example.qeperftestspringmock.models.KafkaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/kafka")
    public ResponseEntity<?> handlePostRequest(@RequestBody KafkaRequest request) {
        logger.info("Request: {}", request);
        kafkaProducer.sendMessage(request.getAppId() + " " + request.getMessage());
        return ResponseEntity.ok().build();
    }

}
