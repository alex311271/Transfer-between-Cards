package ru.netology.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    private int startBalanceFirstCard;
    private int startBalanceSecondCard;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var verificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var dashboardPage = verificationPage.codeInput(verificationCod);
        startBalanceFirstCard = dashboardPage.getFirstCardBalance();
        startBalanceSecondCard = dashboardPage.getSecondCardBalance();



    }


    @Test
    void transferToFirstCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceFirstCard + Integer.parseInt(amount), dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(startBalanceSecondCard - Integer.parseInt(amount), dashboardPage.getSecondCardBalance());
    }


    @Test
    void transferToSecondCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard + Integer.parseInt(amount), dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard - Integer.parseInt(amount), dashboardPage.getFirstCardBalance());
    }

    @Test
    void cancelTransferToFirstCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.cancelTransferToCard(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard, dashboardPage.getFirstCardBalance());
    }


    @Test
    void cancelTransferToSecondCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.cancelTransferToCard(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard, dashboardPage.getFirstCardBalance());
    }

    @Test
    void transferToFirstCardAmountGreaterCardBalance() {
        int amount = startBalanceSecondCard + 100;
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        transferPage.cardErrorMessage(String.valueOf(amount), DataHelper.getSecondCardInfo().getCardNumber());
    }


    @Test
     void transferToSecondCardAmountGreaterCardBalance() {
        int amount = startBalanceFirstCard + 100;
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        transferPage.cardErrorMessage(String.valueOf(amount), DataHelper.getFirstCardInfo().getCardNumber());
    }



    @Test
    void errorMessageSecondCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        transferPage.cardErrorMessage(amount, null);
    }

    @Test
    void errorMessageFirstCard() {
        String amount = "1000";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        transferPage.cardErrorMessage(amount, null);
    }

    @Test
    void transferToFirstCardDoubleAmount() {
        String amount = "10.65";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceFirstCard - Double.parseDouble(amount), dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(startBalanceSecondCard + Double.parseDouble(amount), dashboardPage.getSecondCardBalance());
    }

    @Test
    void transferToSecondCardDoubleAmount() {
        String amount = "10.65";
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.selectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard - Double.parseDouble(amount), dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard + Double.parseDouble(amount), dashboardPage.getFirstCardBalance());
    }


}
