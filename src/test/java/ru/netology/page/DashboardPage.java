package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement infoCard = $("[data-test-id=dashboard]");
    private ElementsCollection listCardButton = $$("[data-test-id=action-deposit");
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        infoCard.shouldBe(Condition.visible);
        listCardButton.get(0).shouldBe(Condition.visible);
        listCardButton.get(1).shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        val text = cards.get(0).text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.get(1).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage SelectCard(int cardIndex) {
        listCardButton.get(cardIndex).click();
        return new TransferPage();
    }

}
