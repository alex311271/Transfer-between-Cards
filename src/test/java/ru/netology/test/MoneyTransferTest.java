package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }



    @Test
    void transferToFirstCard() {
        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var firstCardIndex = DataHelper.getFirstCardIndex(loginData);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getFirstCardBalance();
        var TransferPage = dashboardPage.firstCardSelection(firstCardIndex);
        var secondCard = DataHelper.getSecondCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToFirstCard(amount, secondCard);
        int expected = startCardBalance + amount;
        int actual = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void cancelTransferToFirstCard() {
        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var firstCardIndex = DataHelper.getFirstCardIndex(loginData);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getFirstCardBalance();
        var TransferPage = dashboardPage.firstCardSelection(firstCardIndex);
        var secondCard = DataHelper.getSecondCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.cancelTransferToFirstCard(amount, secondCard);
        int expected = startCardBalance;
        int actual = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void transferToSecondCard() {
        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getSecondCardBalance();
        var TransferPage = dashboardPage.secondCardSelection(secondCardIndex);
        var firstCard = DataHelper.getFirstCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToSecondCard(amount, firstCard);
        int expected = startCardBalance + amount;
        int actual = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void cancelTransferToSecondCard() {
        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getSecondCardBalance();
        var TransferPage = dashboardPage.secondCardSelection(secondCardIndex);
        var firstCard = DataHelper.getFirstCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.cancelTransferToSecondCard(amount, firstCard);
        int expected = startCardBalance;
        int actual = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expected, actual);
    }



    @Test
     void transferToSecondCardAmountGreaterCardBalance() {

        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getSecondCardBalance();
        int amount = startCardBalance * 2;
        var TransferPage = dashboardPage.secondCardSelection(secondCardIndex);
        var firstCard = DataHelper.getFirstCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToSecondCard(amount, firstCard);
        $("[data-test-id=error-notification]").shouldBe(Condition.visible);
    }

    @Test
    void transferToFirstCardAmountGreaterCardBalance() {

        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var firstCardIndex = DataHelper.getFirstCardIndex(loginData);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getSecondCardBalance();
        int amount = startCardBalance * 2;
        var TransferPage = dashboardPage.firstCardSelection(firstCardIndex);
        var secondCard = DataHelper.getSecondCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToFirstCard(amount, secondCard);
        $("[data-test-id=error-notification]").shouldBe(Condition.visible);
    }

    @Test
    void errorMessageSecondCard() {

        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getSecondCardBalance();
        var TransferPage = dashboardPage.secondCardSelection(secondCardIndex);
        var firstCard = DataHelper.getFirstCard(loginData);
        var transferPage = new TransferPage();
        transferPage.secondCardErrorMessage(amount, firstCard);
    }

    @Test
    void errorMessageFirstCard() {
        int amount = 1000;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var firstCardIndex = DataHelper.getFirstCardIndex(loginData);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        int startCardBalance = dashboardPage.getFirstCardBalance();
        var TransferPage = dashboardPage.firstCardSelection(firstCardIndex);
        var secondCard = DataHelper.getSecondCard(loginData);
        var transferPage = new TransferPage();
        transferPage.firstCardErrorMessage(amount, secondCard);
    }

    @Test
    void transferToFirstCardDoubleAmount() {
        double amount = 100.65;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var firstCardIndex = DataHelper.getFirstCardIndex(loginData);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        double startCardBalance = dashboardPage.getFirstCardBalance();
        var TransferPage = dashboardPage.firstCardSelection(firstCardIndex);
        var secondCard = DataHelper.getSecondCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToFirstCardDoubleAmount(amount, secondCard);
        double expected = startCardBalance + amount;
        double actual = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void transferToSecondCardDoubleAmount() {
        double amount = 100.65;
        var loginPage = new LoginPage();
        var loginData = DataHelper.getLoginData();
        var VerificationPage = loginPage.logInn(loginData);
        var verificationCod = DataHelper.getVerificationCod(loginData);
        var verificationPage = new VerificationPage();
        var DashboardPage = verificationPage.codeInput(verificationCod);
        var secondCardIndex = DataHelper.getSecondCardButton(loginData);
        var dashboardPage = new DashboardPage();
        double startCardBalance = dashboardPage.getSecondCardBalance();
        var TransferPage = dashboardPage.secondCardSelection(secondCardIndex);
        var firstCard = DataHelper.getFirstCard(loginData);
        var transferPage = new TransferPage();
        DashboardPage = transferPage.transferToSecondCardDoubleAmount(amount, firstCard);
        double expected = startCardBalance + amount;
        double actual = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expected, actual);
    }


}
