package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    SelenideElement codInput = $("[data-test-id=code] input");
    SelenideElement verifyButton = $("[data-test-id=action-verify");

    public VerificationPage() {
        $("[data-test-id=code] input").shouldBe(Condition.visible);
    }

    public DashboardPage codeInput(DataHelper.VerificationCod cod) {
        codInput.setValue(cod.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
