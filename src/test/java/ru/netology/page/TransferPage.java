package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

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


    public DashboardPage transferToCard(String amount, String cardNumber) {
        amountInput.setValue(amount);
        cardOutNumber.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }


    public void cardErrorMessage(String amount, String cardNumber) {
        amountInput.setValue(amount);
        cardOutNumber.setValue(cardNumber);
        transferButton.click();
        errorMessage.shouldBe(Condition.visible);

    }


    public DashboardPage cancelTransferToCard(String amount, String cardNumber) {
        amountInput.setValue(amount);
        cardOutNumber.setValue(cardNumber);
        cancelButton.click();
        return new DashboardPage();
    }

}
