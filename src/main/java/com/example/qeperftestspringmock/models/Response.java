package com.example.qeperftestspringmock.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String appId;
    private String status;
    private String lastStatusChangeDateTime;
    private String number;

}
