# Дипломный проект по профессии «Тестировщик»

Автоматизация тестирования сервиса, взаимодействующего с СУБД.

## Начало работы

Установить на ПК:
1. JAVA JDK 11
2. IntelliJ IDEA
3. Docker
4. GIT

## Установка и запуск тестов

1. Запустить контейнеры командой: docker-compose up
2. Запустить SUT командой:

для MySQL: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar

для Postresql: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres" -jar artifacts/aqa-shop.jar

3. Запустить тесты командой:

для MySQL: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"

для Postresql: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/postgres"

4. Сенерировать отчет спомощью Allure: ./gradlew allureReport