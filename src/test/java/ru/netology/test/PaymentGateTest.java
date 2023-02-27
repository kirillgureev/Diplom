package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.HomePage;
import ru.netology.page.PaymentGate;

import static com.codeborne.selenide.Selenide.open;

public class PaymentGateTest {
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
    void submittingAFormWithAnAllowedCard() { //  Отправка формы с разрешенной картой
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.successfulOperation();
        var paymentStatus = SqlHelper.getPaymentApprovedStatus();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void submittingAFormWithAnUnresolvedCard() { // Отправка формы с неразрешенной картой
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberDeclined();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
        var paymentStatus = SqlHelper.getPaymentDeclinedStatus();
        Assertions.assertEquals("DECLINED", paymentStatus);
    }


    @Test
    void sendingTheFormWithEnteredZerosInTheCardNumberField2() { //  Отправка формы с ведёнными 16 нулями в поле "Номер карты"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberZero2();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void sendingAFormWithAShortCardNumber() { //  Отправка формы с коротким номером карты
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.shortCard();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongFormatMessage();
    }

    @Test
    void submitFormWithZeroMonth() { //  Отправка формы с нулевым месяцем
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.zeroMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkIncorrectMonthFormat();
    }

    @Test
    void submittingTheFormWithTheThirteenthMonth() { //  Отправка формы с тринадцатым месяцем
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.thirteenthMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkIncorrectMonth();
    }

    @Test
    void submittingTheFormWithThePastYear() { //  Отправка формы с прошедшим годом
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.lastYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkExpiredYear();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheYearField() { //  Отправка формы с введённым нулём в поле "Год"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.zeroYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongYearFormat();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheYearField2() { //  Отправка формы с введёнными нулями в поле "Год"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.zeroYear2();
        var owner = DataHelper.owner();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkExpiredYear();
    }

    @Test
    void submittingAFormWithAnEmptyOwnerField() { //  Отправка формы с кириллицей в поле "Владелец"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.notEngName();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void submittingAFormWithIncorrectCVC() { //  Отправка формы с некорректным "CVC/CVV"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.wrongCVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongCVCFormat();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheCVCField() { //  Отправка формы с веденными нулями в поле "CVC/CVV"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.owner();
        var cvc = DataHelper.zeroCVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void emptyForm() { // Отправка пустой формы
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.emptyCardField();
        var month = DataHelper.emptyFields(); //DataHelper.emptyMonthField();
        var year = DataHelper.emptyFields(); //DataHelper.emptyYearField();
        var owner = DataHelper.emptyFields(); //DataHelper.emptyOwnerField();
        var cvc = DataHelper.emptyFields(); //DataHelper.emptyCvcField();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongFormatMessage();
        paymentGate.checkIncorrectMonthFormat();
        paymentGate.checkWrongYearFormat();
        paymentGate.checkOwnerEmptyField();
        paymentGate.checkWrongCVCFormat();
    }

    @Test
    void submittingTheFormWithNumbersEnteredInTheOwnerField() { // Отправка формы с введенными цифрами в поле "Владелец"
        var homePage = new HomePage();
        homePage.DebitCardPayment();
        var cardNumber = DataHelper.cardNumberApproved();
        var month = DataHelper.currentMonth();
        var year = DataHelper.currentYear();
        var owner = DataHelper.ownerWithNumbers();
        var cvc = DataHelper.CVC();
        var paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }
}