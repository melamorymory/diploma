package test;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PaymentGateHappyTest {
    private SelenideElement nameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    DataHelper data = new DataHelper();

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        data.clickThePaymentButton();
    }

    // all tests have failed whit postgres
    @Test
    public void shouldCarryOutTheOperation() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCarryOutTheOperationWithNameWith2Spaces() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("Bang Chistopher Chan");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCarryOutTheOperationWithNameWithDash() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("Marie-Antoinette");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCarryOutTheOperationWithNameOfTwoLetters() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("I N");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCarryOutTheOperationWithNewCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(5);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCarryOutTheOperationWithOldCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(0);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }
}
