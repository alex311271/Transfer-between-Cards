package ru.netology.test;

import com.codeborne.selenide.Configuration;

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
    private double startBalanceCardFirst;
    private double startBalanceCardSecond;

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
        startBalanceCardFirst = dashboardPage.getFirstCardBalance();
        startBalanceCardSecond = dashboardPage.getSecondCardBalance();


    }


    @Test
    void transferToFirstCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceFirstCard + amount, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(startBalanceSecondCard - amount, dashboardPage.getSecondCardBalance());
    }


    @Test
    void transferToSecondCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCard(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard + amount, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard - amount, dashboardPage.getFirstCardBalance());
    }

    @Test
    void cancelTransferToFirstCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.cancelTransferToCard(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard, dashboardPage.getFirstCardBalance());
    }


    @Test
    void cancelTransferToSecondCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.cancelTransferToCard(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceFirstCard, dashboardPage.getFirstCardBalance());
    }

    @Test
    void transferToFirstCardAmountGreaterCardBalance() {
        int amount = startBalanceSecondCard;
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        transferPage.transferToCardAmountGreaterCardBalance(amount, DataHelper.getSecondCardInfo().getCardNumber());
    }


    @Test
     void transferToSecondCardAmountGreaterCardBalance() {
        int amount = startBalanceFirstCard;
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        transferPage.transferToCardAmountGreaterCardBalance(amount, DataHelper.getFirstCardInfo().getCardNumber());
    }



    @Test
    void errorMessageSecondCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        transferPage.CardErrorMessage(amount);
    }

    @Test
    void errorMessageFirstCard() {
        int amount = DataHelper.getAmountInfo().getAmountInt();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        transferPage.CardErrorMessage(amount);
    }

    @Test
    void transferToFirstCardDoubleAmount() {
        double amount = DataHelper.getAmountInfo().getAmountDouble();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCardDoubleAmount(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceCardFirst - amount, dashboardPage.getFirstCardBalance());
        Assertions.assertEquals(startBalanceCardSecond + amount, dashboardPage.getSecondCardBalance());
    }

    @Test
    void transferToSecondCardDoubleAmount() {
        double amount = DataHelper.getAmountInfo().getAmountDouble();
        var dashboardPage = new DashboardPage();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferPage = dashboardPage.SelectCard(cardInfo.getCardIndex());
        dashboardPage = transferPage.transferToCardDoubleAmount(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(startBalanceSecondCard - amount, dashboardPage.getSecondCardBalance());
        Assertions.assertEquals(startBalanceCardFirst + amount, dashboardPage.getFirstCardBalance());
    }


}
