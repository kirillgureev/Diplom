package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class PaymentGate {
    private final SelenideElement cardNumberField = $x("//span[text() = 'Номер карты']/..//input");
    private final SelenideElement fieldMonth = $x("//span[text() = 'Месяц']/..//input");
    private final SelenideElement fieldYear = $x("//span[text() = 'Год']/..//input");
    private final SelenideElement fieldOwner = $x("//span[text() = 'Владелец']/..//input");
    private final SelenideElement fieldCVC = $x("//span[text() = 'CVC/CVV']/..//input");
    private final SelenideElement buttonNext = $x("//span[text()='Продолжить']/../..");
    private final SelenideElement successNotification = $x("//div[contains(@class, 'notification_status_ok')]");
    private final SelenideElement errorNotification = $x("//div[contains(@class, 'notification_status_error')]");
    private final SelenideElement wrongFormatMessage = $x("//span[@class = 'input__top' and text() = 'Номер карты']/..//span[text()='Неверный формат']");
    private final SelenideElement incorrectMonthFormat = $x("//span[@class = 'input__top' and text() = 'Месяц']/..//span[text()='Неверный формат']");
    private final SelenideElement incorrectMonth = $x("//span[@class = 'input__top' and text() = 'Месяц']/..//span[text()='Неверно указан срок действия карты']");
    private final SelenideElement expiredYear = $x("//span[@class = 'input__top' and text() = 'Год']/..//span[text()='Истёк срок действия карты']");
    private final SelenideElement wrongYearFormat = $x("//span[@class = 'input__top' and text() = 'Год']/..//span[text()='Неверный формат']");
    private final SelenideElement emptyOwnerField = $x("//span[@class = 'input__top' and text() = 'Владелец']/..//span[text()='Поле обязательно для заполнения']");
    private final SelenideElement incorrectCVCFormat = $x("//span[@class = 'input__top' and text() = 'CVC/CVV']/..//span[text()='Неверный формат']");

    public void fillingFieldsFormat(DataHelper.CardNumber info, String month, String year, String owner, String cvc) {
        cardNumberField.setValue(info.getCardNumber());
        fieldMonth.setValue(month);
        fieldYear.setValue(year);
        fieldOwner.setValue(owner);
        fieldCVC.setValue(cvc);
        buttonNext.click();
    }

    public void successfulOperation() { //успешная операция
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorOperation() { //операция с ошибкой
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormatMessage() { // карта, неверный формат
        wrongFormatMessage.shouldBe(Condition.visible);
    }

    public void checkIncorrectMonthFormat() { // неправильный формат месяца
        incorrectMonthFormat.shouldBe(Condition.visible);
    }

    public void checkIncorrectMonth() { // неверно срок действия карты поля месяц
        incorrectMonth.shouldBe(Condition.visible);
    }

    public void checkExpiredYear() { //просроченный год
        expiredYear.shouldBe(Condition.visible);
    }

    public void checkWrongYearFormat() { // неправильный формат года
        wrongYearFormat.shouldBe(Condition.visible);
    }

    public void checkOwnerEmptyField() { //пустое поле владельца
        emptyOwnerField.shouldBe(Condition.visible);
    }

    public void checkWrongCVCFormat() { // неверный формат CVC
        incorrectCVCFormat.shouldBe(Condition.visible);
    }
}