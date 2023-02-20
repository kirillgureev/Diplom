package ru.netology.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    public static String cardNumberApproved() { //номер карты утвержден

        return "4444 4444 4444 4441";
    }

    public static String cardNumberDeclined() { //номер карты отклонен

        return "4444 4444 4444 4442";
    }

    public static String cardNumberZero2() { //номер карты нулевой2

        return "0000 0000 0000 0000";
    }

    public static String shortCard() { //короткий номер карты

        return "4444";
    }

    public static String emptyCardField() {//пустое поле номера карты

        return "";
    }

    public static String currentMonth() { //текущий месяц
        LocalDate currentMonth = LocalDate.now();
        int month = currentMonth.getMonthValue();
        return String.format("%02d", month);
    }

    public static String zeroMonth() { //нулевой месяц
        return "0";
    }

    public static String thirteenthMonth() { //тринадцатый месяц
        return "13";
    }

    public static String currentYearV2() {//текущи год в.2
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String formattedString = localDate.format(formatter);
        return formattedString;
    }

    public static String lastYear() { //прошедший год
        LocalDate lastYear = LocalDate.now();
        int year = lastYear.getYear();
        return Integer.toString(year);
    }

    public static String zeroYear() { //нулевой год
        return "0";
    }

    public static String zeroYear2() { //нулевой год2
        return "00";
    }

    public static String owner() { //владелец
        return faker.name().fullName();
    }

    public static String ownerWithNumbers() { //поле владелец цифрами
        return "123456";
    }

    public static String notEngName() { //имя владельца кириллицей
        return "Кирилл Гуреев";
    }

    public static String CVC() { //правильный cvc
        return faker.number().digits(3);
    }

    public static String wrongCVC() { //не правильный cvc
        return faker.number().digits(2);
    }

    public static String zeroCVC() { //нулевой cvc
        return "000";
    }

    public static String emptyFields() { //пустые поля
        return "";
    }
}