package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

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


    public DashboardPage transferToFirstCard(int amount, DataHelper.SecondCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferToFirstCardDoubleAmount(double amount, DataHelper.SecondCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferToSecondCard(int amount, DataHelper.FirstCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferToSecondCardDoubleAmount(double amount, DataHelper.FirstCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void secondCardErrorMessage(int amount, DataHelper.FirstCard number) {
        amountInput.setValue(String.valueOf(amount));
        transferButton.click();
        errorMessage.shouldBe(Condition.visible);
    }

    public void firstCardErrorMessage(int amount, DataHelper.SecondCard number) {
        amountInput.setValue(String.valueOf(amount));
        transferButton.click();
        errorMessage.shouldBe(Condition.visible);
    }

    public DashboardPage cancelTransferToFirstCard(int amount, DataHelper.SecondCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        cancelButton.click();
        return new DashboardPage();
    }

    public DashboardPage cancelTransferToSecondCard(int amount, DataHelper.FirstCard number) {
        amountInput.setValue(String.valueOf(amount));
        cardOutNumber.setValue(number.getNumber());
        cancelButton.click();
        return new DashboardPage();
    }
}
