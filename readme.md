## Заглушка некоего вымышленного сервиса "Сервис создания черновика заявления на открытие аккредитива"
Заглушка эмулирует сценарий создания черновика заявления на открытие аккредитива.

Пример запроса:
```
{
"name": "ОАО Ромашка",
"inn": "7707083894",
"ogrn": "1027700132195",
"kpp": "973643001",
"appId": "4097643769930881329"
}
```

Пример ответа:

```
{
"appId": "4097643769930881329",
"status": "IN_PROGRESS",
"lastStatusChangeDateTime": "2024-02-29T16:30:04.528832+03:00",
"number": "79542"
}
```

## Запуск приложения
1. Запустите spring boot приложение командой `./mvnw spring-boot:run`
2. Отправьте запрос с помощью утилиты командной строки curl. Тело запроса содержится в файле [request.json](./request.json).
   Выполните команду `curl -X POST -d "@request.json" -H "Content-Type: application/json" http://localhost:8080/mock`
   В ответ придет примерно такой JSON:
```
{
    "appId": "4097643769930881329",
    "status": "IN_PROGRESS",
    "lastStatusChangeDateTime": "2024-02-29T16:30:04.528832+03:00",
    "number": "79542"
}
```

## Настраиваемые параметры
в файле application.properties можно задать значение следующих параметров:

1. Задержка ответа в миллисекундах, значение по умолчанию 2 сек

   `mockResponse.delay=2000`

2. Процент ошибок, значение по умолчанию 10% (в заданном проценте случаев сервис возвращает Status: 500 INTERNAL_SERVER_ERROR
   )

   `error.rate=10`

## Метрики

* в проекте настроена выгрузка метрик в формате Prometheus посредством Spring Actuator(Micrometer) http://localhost:8080/actuator/prometheus, 
визуализация в Grafana была реализована дашбордом `Micrometer Spring Throughput` (ID: 5373)

* тестовая нагрузка подавалась нструментом k6, `k6 run --vus 100 --duration 3m http_post.js`

* результаты нагрузки представлены на скрине дашборда ниже

![Metrics](./metrics.png?raw=true "Grafana dashboard")
