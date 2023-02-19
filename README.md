# Дипломный проект по профессии «Тестировщик»
## План автоматизации и отчетные документы:
1. [План автоматизации](Plan.md)
2. [Отчётные документы по итогам автоматизированного тестирования](Report.md)
3. [Отчётные документы по итогам автоматизации](Summary.md)


Автоматизация тестирования сервиса, взаимодействующего с СУБД.
[План автоматизации](Plan.md)

## Начало работы

Установить на ПК:
1. JAVA JDK 11
2. IntelliJ IDEA
3. Docker
4. GIT

## Установка и запуск тестов

1. Запустить контейнеры командой: docker-compose up
2. Запустить SUT командой:

для MySQL: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar

для Postresql: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar aqa-shop.jar

3. Запустить тесты командой:

для MySQL: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"

для Postresql: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app

4. Сенерировать отчет спомощью Allure: ./gradlew allureReport