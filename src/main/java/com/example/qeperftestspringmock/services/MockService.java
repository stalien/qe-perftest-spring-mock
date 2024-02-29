package com.example.qeperftestspringmock.services;

import com.example.qeperftestspringmock.models.StatusValues;
import com.example.qeperftestspringmock.models.Request;
import com.example.qeperftestspringmock.models.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MockService {

    @Value("${response.delay}")
    private int responseDelay;
    @Value("${error.rate}")
    private int errorRate;

    public Response generateResponse(Request request) throws InterruptedException {
        // Задержка ответа
        TimeUnit.SECONDS.sleep(responseDelay);

        // Вариативность ответов status
        Random random = new Random();
        StatusValues statusValue = StatusValues.values()[random.nextInt(StatusValues.values().length)];

        // Возвращаем данные appId из входящего запроса в ответный JSON
        String appId = request.getAppId();

        // Время изменения статуса
        OffsetDateTime now = OffsetDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // Некий рандомный номер заявления
        int number = random.nextInt(100000);

        return Response.builder()
                .appId(appId)
                .status(statusValue.toString())
                .lastStatusChangeDateTime(formattedNow)
                .number(String.valueOf(number))
                .build();
    }

    public boolean isError() {
        // Эмуляция процента ошибок
        Random random = new Random();
        int randomValue = random.nextInt(100);
        return randomValue < errorRate;
    }
}
