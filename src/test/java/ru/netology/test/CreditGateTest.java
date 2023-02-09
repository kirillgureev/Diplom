package ru.netology.test;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CreditGate;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;

public class CreditGateTest {
    @BeforeAll

    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void shouldLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }


    @Test
    void submittingAFormWithAnAllowedCard() { // 1 Отправка формы с разрешенной картой
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.successfulOperation();
    }
    @Test
    void submittingAFormWithAnUnresolvedCard(){ //2 Отправка формы с неразрешенной картой
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberDeclined();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();
    }
    @Test
    void sendingTheFormWithEnteredZerosInTheCardNumberField(){ // 3 Отправка формы с ведёнными нулями в поле "Номер карты"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberZero();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();
    }
    @Test
    void sendingAFormWithAShortCardNumber(){ // 4 Отправка формы с коротким номером карты
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.shortCard();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongFormatMessage();
    }
    @Test
    void sendingAFormWithAnEmptyFieldCardNumber(){ // 5 Отправка формы с пустым полем "Номер карты"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.emptyCardField();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongFormatMessage();
    }
    @Test
    void submitFormWithZeroMonth(){ // 6 Отправка формы с нулевым месяцем
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.zeroMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkIncorrectMonth();
    }
    @Test
    void submittingTheFormWithTheThirteenthMonth(){ // 7 Отправка формы с тринадцатым месяцем
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.thirteenthMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkIncorrectMonth();
    }
    @Test
    void submittingAFormWithAnEmptyMonthField(){ // 8 Отправка формы с пустым полем "Месяц"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.emptyMonthField();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkIncorrectMonthFormat();
    }
    @Test
    void submittingTheFormWithThePastYear(){// 9 Отправка формы с прошедшим годом
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.lastYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkExpiredYear();
    }
    @Test
    void submittingTheFormWithZerosEnteredInTheYearField(){ // 10 Отправка формы с введёнными нулями в поле "Год"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.zeroYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkExpiredYear();
    }
    @Test
    void submittingAFormWithAnEmptyYearField(){ // 11 Отправка формы с пустым полем "Год"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.emptyYearField();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongYearFormat();
    }
    @Test
    void submittingTheFormWithNumbersEnteredInTheOwnerField(){ //12 Отправка формы с введенными цифрами в поле "Владелец"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.ownerWithNumbers();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();
    }
    @Test
    void submittingAFormWithAnEmptyOwnerField(){ // 13 Отправка формы с пустым полем "Владелец"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.emptyOwnerField();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkOwnerEmptyField();
    }
    @Test
    void submittingAFormWithIncorrectCVC(){ // 14 Отправка формы с некорректным "CVC/CVV"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.wrongCVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongCVCFormat();
    }
    @Test
    void submittingAFormWithAnEmptyCVCField(){ // 15 Отправка формы с пустым полем "CVC/CVV"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.emptyCvcField();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongCVCFormat();
    }
}