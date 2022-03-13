package test;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PaymentGateSadTest {
    private SelenideElement monthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private SelenideElement yearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private SelenideElement nameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvcField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private SelenideElement cardField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");
    DataHelper data = new DataHelper();


    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        data.clickThePaymentButton();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithNameWithCyrillic() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("Ольга Лебедева");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithNameWithHieroglyphs() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("ごじょうさとる");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithNumbersInTheNameField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("12345");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInTheNameField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue(",./+=");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();

    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithSpacesInTheNameField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("  ");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
        data.getErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithNameOfOneLetter() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("L");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithDeclinedCard() {
        data.getDeclinedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithNonExistentCard() {
        cardField.setValue("0000 1111 2222 3333");
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInCardField() {
        cardField.setValue("Hello");
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getCardErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInCardField() {
        cardField.setValue("/.,=-");
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getCardErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInCardField() {
        cardField.setValue(" ");
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getCardErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithExpiredCard() {
        data.getValidName();
        data.getMonth(1);
        data.getYear(5);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithLongExpiredCard() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(6);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearErrorNotification();
    }

    //упав
    @Test
    public void shouldNotCarryOutTheOperationWithWrongMonth() {
        data.getValidName();
        monthField.setValue("13");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInMonthField() {
        data.getValidName();
        monthField.setValue("Hello");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInMonthField() {
        data.getValidName();
        monthField.setValue("/.,=-");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInMonthField() {
        data.getValidName();
        monthField.setValue(" ");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInYearField() {
        data.getValidName();
        data.getMonth(0);
        yearField.setValue("Hello");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInYearField() {
        data.getValidName();
        data.getMonth(0);
        yearField.setValue("/.,=-");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInYearField() {
        data.getValidName();
        data.getMonth(0);
        yearField.setValue(" ");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithTwoNumbersInCVCField() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("00");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    //упав
    @Test
    public void shouldCarryOutTheOperationWithFourNumbersInCVCField() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("0000");
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInCVCField() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("Hello");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInCVCField() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("/.,=-");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInCVCField() {
        data.getValidName();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue(" ");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithEmptyForm() {
        data.clickTheContinueButton();
        data.getCardErrorNotification();
        data.getMonthFormatErrorNotification();
        data.getYearFormatErrorNotification();
        data.getNameErrorNotification();
        data.getCVCErrorNotification();
    }


}
