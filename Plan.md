# План автоматизации по дипломному проекту

## Перечень автоматизируемых сценариев к обычной оплате

Предусловия: Зайти на страницу http://localhost:8080/ и нажать кнопку "Купить"

### 1 Отправка формы с допустимой картой

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об успешной операции.

### 2 Отправка формы с недопустимой картой

1. Ввести номер карты "4444 4444 4444 4442"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 3 Отправка пустой формы

1. Ввести номер карты ""
2. Ввести в поле “Месяц” ()
3. Ввести в поле “Год” ()
4. Ввести в поле “Владелец” ()
5. Ввести в поле "CVC/CVV" ()
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Все поля подсвечиваются красным, ниже подсказки по их заполнению.

### 4 Отправка формы с ведёнными 16 нулями в поле "Номер карты"

1. Ввести номер карты "0000000000000000"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 5 Отправка формы с неполным номером карты

1. Ввести номер карты "4444"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 6 Отправка формы с нулевым месяцем

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (0)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 7 Отправка формы с тринадцатым месяцем

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (13)
3. Ввести в поле “Год” (22)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверно указан срок действия карты"

### 8 Отправка формы с прошедшим годом

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (15)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Истёк срок действия карты"

### 9 Отправка формы с введённым нулём в поле "Год"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (0)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 10 Отправка формы с введёнными нулями в поле "Год"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (00)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Истёк срок действия карты"

### 11 Отправка формы с введенными цифрами в поле "Владелец"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (123456)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 12 Отправка формы с введенной кириллицей в поле "Владелец"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Кирилл Гуреев)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 13 Отправка формы с некорректным "CVC/CVV"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (00)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 14 Отправка формы с веденными нулями в поле "CVC/CVV"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (000)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

## Перечень автоматизируемых сценариев к кредитному сервису

Предусловия: Зайти на страницу http://localhost:8080/ и нажать кнопку "Купить в кредит"

### 15 Отправка формы с допустимой картой

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об успешной операции.

### 16 Отправка формы с недопустимой картой

1. Ввести номер карты "4444 4444 4444 4442"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 17 Отправка пустой формы

1. Ввести номер карты ""
2. Ввести в поле “Месяц” ()
3. Ввести в поле “Год” ()
4. Ввести в поле “Владелец” ()
5. Ввести в поле "CVC/CVV" ()
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Все поля подсвечиваются красным, ниже подсказки по их заполнению.

### 18 Отправка формы с ведёнными 16 нулями в поле "Номер карты"

1. Ввести номер карты "0000000000000000"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 19 Отправка формы с коротким номером карты

1. Ввести номер карты "4444"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 20 Отправка формы с нулевым месяцем

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (0)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 21 Отправка формы с тринадцатым месяцем

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (13)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверно указан срок действия карты"

### 22 Отправка формы с прошедшим годом

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (15)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Истёк срок действия карты"

### 23 Отправка формы с нулем в поле "Год"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (0)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 24 Отправка формы с введёнными нулями в поле "Год"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (00)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Истёк срок действия карты"

### 25 Отправка формы с введенными цифрами в поле "Владелец"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (123456)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 26 Отправка формы с кириллицей в поле "Владелец"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Кирилл Гуреев)
5. Ввести в поле "CVC/CVV" (123)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

### 27 Отправка формы с некорректным "CVC/CVV"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (00)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Поле подсвечивается красным цветом, ниже подсказка "Неверный формат"

### 28 Отправка формы с веденными нулями в поле "CVC/CVV"

1. Ввести номер карты "4444 4444 4444 4441"
2. Ввести в поле “Месяц” (12)
3. Ввести в поле “Год” (23)
4. Ввести в поле “Владелец” (Kirill Gureev)
5. Ввести в поле "CVC/CVV" (000)
6. Нажать на кнопку "Продолжить"

Ожидаемый результат: Алерт об ошибке.

# Перечень используемых инструментов с обоснованием выбора

1. IntelliJ IDEA - удобна в использовании, на протяжении всего обучения использовал ее.
2. Allure - для получения понятной и наглядной отчетности.
3. Библиотеки JUnit, Selenide, Lombok - для написания кодов автотестов, web - тестирования, создания баз данных для использования в тестировании.

# Перечень и описание возможных рисков при автоматизации

1. Написание автотестов, в начале, занимает длительное время, которое можно было бы посвятить ручным тестам.
2. Риск возрастания стоимости проекта из-за написания автотестов.

# Интервальная оценка с учетом рисков в часах

Планирую уделять около 10 часов в неделю.

# План сдачи работ: когда будут готовы автотесты, результаты их прогона
Автотесты будут готовы к 28.02.2023.