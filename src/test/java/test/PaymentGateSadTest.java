package test;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.SneakyThrows;
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

    //bug
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

    //bug
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

    //bug
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

    //bug
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

    @Test
    public void shouldNotCarryOutTheOperationWithSpacesInTheNameField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("  ");
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getNameErrorNotification();
    }

    //bug
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

    //bug
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

    //bug
    @Test
    public void shouldNotCarryOutTheOperationWithExpiredCard() {
        data.getApprovedCard();
        data.getMonth(1);
        data.getYear(5);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLongExpiredCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(6);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithWrongMonth() {
        data.getApprovedCard();
        monthField.setValue("13");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInMonthField() {
        data.getApprovedCard();
        monthField.setValue("Hello");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInMonthField() {
        data.getApprovedCard();
        monthField.setValue("/.,=-");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInMonthField() {
        data.getApprovedCard();
        monthField.setValue(" ");
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getMonthFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithLettersInYearField() {
        data.getApprovedCard();
        data.getMonth(0);
        yearField.setValue("Hello");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInYearField() {
        data.getApprovedCard();
        data.getMonth(0);
        yearField.setValue("/.,=-");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInYearField() {
        data.getApprovedCard();
        data.getMonth(0);
        yearField.setValue(" ");
        data.getValidName();
        data.getValidCVC();
        data.clickTheContinueButton();
        data.getYearFormatErrorNotification();
    }

    @Test
    public void shouldNotCarryOutTheOperationWithTwoNumbersInCVCField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("00");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    //bug whit postgres
    @Test
    public void shouldCarryOutTheOperationWithFourNumbersInCVCField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("0000");
        data.clickTheContinueButton();
        data.getSuccessNotification();
    }

    //после нажатия кнопки продолжить вылезает уведомление для поля имени "Это поле обязательно для заполнения"
    @Test
    public void shouldNotCarryOutTheOperationWithLettersInCVCField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("Hello");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    //после нажатия кнопки продолжить вылезает уведомление для поля имени "Это поле обязательно для заполнения"
    @Test
    public void shouldNotCarryOutTheOperationWithSymbolsInCVCField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("/.,=-");
        data.clickTheContinueButton();
        data.getCVCErrorNotification();
    }

    //после нажатия кнопки продолжить вылезает уведомление для поля имени "Это поле обязательно для заполнения"
    @Test
    public void shouldNotCarryOutTheOperationWithSpaceInCVCField() {
        data.getApprovedCard();
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
