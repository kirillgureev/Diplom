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
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.successfulOperation();
        String paymentStatus = SqlHelper.getPaymentApprovedStatus();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void submittingAFormWithAnUnresolvedCard() { // Отправка формы с неразрешенной картой
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberDeclined();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
        String paymentStatus = SqlHelper.getPaymentDeclinedStatus();
        Assertions.assertEquals("DECLINED", paymentStatus);
    }


    @Test
    void sendingTheFormWithEnteredZerosInTheCardNumberField2() { //  Отправка формы с ведёнными 16 нулями в поле "Номер карты"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberZero2();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void sendingAFormWithAShortCardNumber() { //  Отправка формы с коротким номером карты
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.shortCard();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongFormatMessage();
    }

    @Test
    void submitFormWithZeroMonth() { //  Отправка формы с нулевым месяцем
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.zeroMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkIncorrectMonthFormat();
    }

    @Test
    void submittingTheFormWithTheThirteenthMonth() { //  Отправка формы с тринадцатым месяцем
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.thirteenthMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkIncorrectMonth();
    }

    @Test
    void submittingTheFormWithThePastYear() { //  Отправка формы с прошедшим годом
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.lastYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkExpiredYear();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheYearField() { //  Отправка формы с введённым нулём в поле "Год"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.zeroYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongYearFormat();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheYearField2() { //  Отправка формы с введёнными нулями в поле "Год"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.zeroYear2();
        String owner = DataHelper.owner();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkExpiredYear();
    }

    @Test
    void submittingAFormWithAnEmptyOwnerField() { //  Отправка формы с кириллицей в поле "Владелец"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.notEngName();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void submittingAFormWithIncorrectCVC() { //  Отправка формы с некорректным "CVC/CVV"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.wrongCVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongCVCFormat();
    }

    @Test
    void submittingTheFormWithZerosEnteredInTheCVCField() { //  Отправка формы с веденными нулями в поле "CVC/CVV"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.owner();
        String cvc = DataHelper.zeroCVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }

    @Test
    void emptyForm() { // Отправка пустой формы
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.emptyCardField();
        String month = DataHelper.emptyFields(); //DataHelper.emptyMonthField();
        String year = DataHelper.emptyFields(); //DataHelper.emptyYearField();
        String owner = DataHelper.emptyFields(); //DataHelper.emptyOwnerField();
        String cvc = DataHelper.emptyFields(); //DataHelper.emptyCvcField();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.checkWrongFormatMessage();
        paymentGate.checkIncorrectMonthFormat();
        paymentGate.checkWrongYearFormat();
        paymentGate.checkOwnerEmptyField();
        paymentGate.checkWrongCVCFormat();
    }

    @Test
    void submittingTheFormWithNumbersEnteredInTheOwnerField() { // Отправка формы с введенными цифрами в поле "Владелец"
        HomePage homePage = new HomePage();
        homePage.DebitCardPayment();
        String cardNumber = DataHelper.cardNumberApproved();
        String month = DataHelper.currentMonth();
        String year = DataHelper.currentYear();
        String owner = DataHelper.ownerWithNumbers();
        String cvc = DataHelper.CVC();
        PaymentGate paymentGate = new PaymentGate();
        paymentGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        paymentGate.errorOperation();
    }
}