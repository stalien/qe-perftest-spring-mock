package com.example.qeperftestspringmock.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaRequest {

    private String appId;
    private String message;

}
