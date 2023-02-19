package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
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
    void submittingAFormWithAnAllowedCard() { //  Отправка формы с разрешенной картой
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
        val paymentStatus = SqlHelper.getCreditApprovedStatus();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }
    @Test
    void submittingAFormWithAnUnresolvedCard(){ // Отправка формы с неразрешенной картой
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
        val paymentStatus = SqlHelper.getCreditDeclinedStatus();
        Assertions.assertEquals("DECLINED", paymentStatus);
    }
    @Test
    void sendingTheFormWithEnteredZerosInTheCardNumberField2(){ //  Отправка формы с ведёнными 16 нулями в поле "Номер карты"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberZero2();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();
    }
    @Test
    void sendingAFormWithAShortCardNumber(){ //  Отправка формы с коротким номером карты
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
    void submitFormWithZeroMonth(){ //  Отправка формы с нулевым месяцем
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.zeroMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkIncorrectMonthFormat();
    }
    @Test
    void submittingTheFormWithTheThirteenthMonth(){ //  Отправка формы с тринадцатым месяцем
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
    void submittingTheFormWithThePastYear(){//  Отправка формы с прошедшим годом
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
    void submittingTheFormWithZerosEnteredInTheYearField(){ //  Отправка формы с введённым нулём в поле "Год"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.zeroYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkWrongYearFormat();
    }
    @Test
    void submittingTheFormWithZerosEnteredInTheYearField2(){ //  Отправка формы с введёнными нулями в поле "Год"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.zeroYear2();
        val owner = DataHelper.owner();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.checkExpiredYear();
    }
    @Test
    void submittingAFormWithAnEmptyOwnerField(){ //  Отправка формы с кириллицей в поле "Владелец"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.notEngName();
        val cvc = DataHelper.CVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();

    }
    @Test
    void submittingAFormWithIncorrectCVC(){ //  Отправка формы с некорректным "CVC/CVV"
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
    void submittingTheFormWithZerosEnteredInTheCVCField(){ //  Отправка формы с веденными нулями в поле "CVC/CVV"
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.cardNumberApproved();
        val month = DataHelper.currentMonth();
        val year = DataHelper.currentYear();
        val owner = DataHelper.owner();
        val cvc = DataHelper.zeroCVC();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month,year,owner,cvc);
        creditGate.errorOperation();
    }
    @Test
    void emptyForm() { // Отправка пустой формы
        val homePage = new HomePage();
        homePage.CreditPayment();
        val cardNumber = DataHelper.emptyCardField();
        val month = DataHelper.emptyMonthField();
        val year = DataHelper.emptyYearField();
        val owner = DataHelper.emptyOwnerField();
        val cvc = DataHelper.emptyCvcField();
        val creditGate = new CreditGate();
        creditGate.fillingFieldsFormat(cardNumber, month, year, owner, cvc);
        creditGate.checkWrongFormatMessage();
        creditGate.checkIncorrectMonthFormat();
        creditGate.checkWrongYearFormat();
        creditGate.checkOwnerEmptyField();
        creditGate.checkWrongCVCFormat();
    }
    @Test
    void submittingTheFormWithNumbersEnteredInTheOwnerField(){ // Отправка формы с введенными цифрами в поле "Владелец"
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
}