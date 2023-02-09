package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private SelenideElement buyButton = $x("//span[text() = 'Купить']");
    private SelenideElement buyOnCreditButton = $x("//span[text() = 'Купить в кредит']");

    public PaymentGate DebitCardPayment() {
        buyButton.click();
        return new PaymentGate();
    }

    public CreditGate CreditPayment() {
        buyOnCreditButton.click();
        return new CreditGate();
    }
}