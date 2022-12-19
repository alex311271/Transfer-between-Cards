package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {


    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement cardOutNumber = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");


    public TransferPage() {
        amountInput.shouldBe(Condition.visible);
        cardOutNumber.shouldBe(Condition.visible);
        transferButton.shouldBe(Condition.visible);
        cancelButton.shouldBe(Condition.visible);
    }


    public DashboardPage transferToCard(int amount, String cardNumber) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferToCardDoubleAmount(double amount, String cardNumber) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    public void CardErrorMessage(int amount) {
        amountInput.setValue(String.valueOf(amount));
        transferButton.click();
        errorMessage.shouldBe(Condition.visible);
    }


    public DashboardPage cancelTransferToCard(int amount, String cardNumber) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(cardNumber);
        cancelButton.click();
        return new DashboardPage();
    }

    public void transferToCardAmountGreaterCardBalance(int amount, String cardNumber) {
        amountInput.setValue(String.valueOf(amount *2));
        cardOutNumber.setValue(cardNumber);
        transferButton.click();
        errorMessage.shouldBe(Condition.visible);
    }
}
