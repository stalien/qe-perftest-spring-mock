package com.example.qeperftestspringmock.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MockRequest {

    private String appId;
    private String name;
    private String inn;
    private String ogrn;
    private String kpp;

}
