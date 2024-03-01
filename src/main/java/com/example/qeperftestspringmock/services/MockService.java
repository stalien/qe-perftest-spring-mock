package com.example.qeperftestspringmock.services;

import com.example.qeperftestspringmock.models.MockRequest;
import com.example.qeperftestspringmock.models.StatusValues;
import com.example.qeperftestspringmock.models.MockResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MockService {

    @Value("${mockResponse.delay}")
    private int mockResponseDelay;
    @Value("${error.rate}")
    private int errorRate;

    public MockResponse generateResponse(MockRequest mockRequest) throws InterruptedException {
        // Задержка ответа
        TimeUnit.MILLISECONDS.sleep(mockResponseDelay);

        // Вариативность ответов status
        Random random = new Random();
        StatusValues statusValue = StatusValues.values()[random.nextInt(StatusValues.values().length)];

        // Возвращаем данные appId из входящего запроса в ответный JSON
        String appId = mockRequest.getAppId();

        // Время изменения статуса
        OffsetDateTime now = OffsetDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // Некий рандомный номер заявления
        int number = random.nextInt(100000);

        return MockResponse.builder()
                .appId(appId)
                .status(statusValue.toString())
                .lastStatusChangeDateTime(formattedNow)
                .number(String.valueOf(number))
                .build();
    }

    public boolean isError() {
        // Эмуляция заданного процента ошибок
        Random random = new Random();
        int randomValue = random.nextInt(100);
        return randomValue < errorRate;
    }
}
