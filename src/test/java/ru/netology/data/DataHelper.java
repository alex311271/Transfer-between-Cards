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
    public  static class CardInfo {
        private int cardIndex;
        private String cardNumber;
    }
    public static CardInfo getFirstCardInfo() {
        return new CardInfo(0, "5559000000000001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo(1, "5559000000000002");
    }

}
