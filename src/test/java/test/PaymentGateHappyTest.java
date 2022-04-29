package test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static data.DBHelper.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentGateHappyTest {
    private SelenideElement nameField = $(".form-field:nth-child(3) .input-group__input-case:first-child .input__control");
    private SelenideElement cvcField = $(".form-field:nth-child(3) .input-group__input-case:last-child .input__control");
    DataHelper data = new DataHelper();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        data.clickThePaymentButton();
    }

    @SneakyThrows
    @AfterEach
    public void cleanDatabase() {
        deleteData();
    }


    @Test
    public void shouldCarryOutTheOperation() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
        assertNotNull(getPaymentStatus());
    }

    @Test
    public void shouldCarryOutTheOperationWithNameWith2Spaces() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("Bang Christopher Chan");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getSuccessNotification();
        assertNotNull(getPaymentStatus());
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
        assertNotNull(getPaymentStatus());
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
        assertNotNull(getPaymentStatus());
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
        assertNotNull(getPaymentStatus());
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
        assertNotNull(getPaymentStatus());
    }

    @Test
    public void shouldCarryOutTheOperationWithFourNumbersInCVCField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("0000");
        data.clickTheContinueButton();
        data.getSuccessNotification();
        assertNotNull(getPaymentStatus());
    }
}
