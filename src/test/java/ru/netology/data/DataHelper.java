package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardNumber {
        private String cardNumber;
    }

    private static Faker faker = new Faker(new Locale("en"));

    public static CardNumber cardNumberApproved() { //номер карты утвержден
        return new CardNumber("4444 4444 4444 4441");
    }

    public static CardNumber cardNumberDeclined() { //номер карты отклонен
        return new CardNumber("4444 4444 4444 4442");
    }

    public static CardNumber cardNumberZero() { //номер карты нулевой
        return new CardNumber("0000 0000 0000");
    }

    public static CardNumber shortCard() { // короткая карта
        return new CardNumber("4444");
    }

    public static CardNumber emptyCardField() {// пустое поле карты
        return new CardNumber("");
    }

    public static String currentMonth() { //текущий месяц
        LocalDate currentMonth = LocalDate.now();
        int month = currentMonth.getMonthValue();
        return String.format("%02d", month);
    }

    public static String zeroMonth() { // нулевой месяц
        return "0";
    }

    public static String thirteenthMonth() { // тринадцатый месяц
        return "13";
    }

    public static String emptyMonthField() { // пустое поле месяц
        return "";
    }

    public static String currentYear() { //текущий год
        LocalDate currentYear = LocalDate.now();
        int year = currentYear.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String lastYear() { //прошедший год
        LocalDate currentYear = LocalDate.now();
        int year = currentYear.getYear() - 2006;
        return Integer.toString(year);
    }

    public static String zeroYear() { // нулевой год
        return "0";
    }

    public static String emptyYearField() { // пустое поле год
        return "";
    }

    public static String owner() { //владелец
        return faker.name().fullName();
    }

    public static String ownerWithNumbers() { // поле владелец цифрами
        return "123456";
    }

    public static String emptyOwnerField() { // пустое поле владелец
        return "";
    }

    public static String CVC() { //правильный cvc
        return faker.number().digits(3);
    }

    public static String wrongCVC() { // не правильный cvc
        return faker.number().digits(2);
    }

    public static String emptyCvcField() { //пустое поле cvc
        return "";
    }
}