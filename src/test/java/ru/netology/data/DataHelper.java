package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class LoginData {
        private String login;
        private String password;
    }

    public static LoginData getLoginData() {
        return new LoginData("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCod {
        private String code;
    }

    public static VerificationCod getVerificationCod(LoginData loginData) {
        return new VerificationCod("12345");
    }

    @Value
    public static class FirstCard {
        private String number;
    }

    public static FirstCard getFirstCard(LoginData loginData) {
        return new FirstCard("5559000000000001");
    }

    @Value
    public static class SecondCard {
        private String number;
    }

    public static SecondCard getSecondCard(LoginData loginData) {
        return new SecondCard("5559000000000002");
    }

    @Value
    public  static class FirstCardIndex {
        private int indexButton;
    }
    public static FirstCardIndex getFirstCardIndex(LoginData loginData) {
        return new FirstCardIndex(0);
    }

    @Value
    public  static class SecondCardIndex {
        private int indexButton;
    }
    public static SecondCardIndex getSecondCardButton(LoginData loginData) {
        return new SecondCardIndex(1);
    }
}
