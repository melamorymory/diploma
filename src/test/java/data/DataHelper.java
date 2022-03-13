package data;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DataHelper {

    private SelenideElement paymentButton = $x("//*[@id=\"root\"]/div/button[1]");
    private SelenideElement creditButton = $x("//*[@id=\"root\"]/div/button[2]");
    private SelenideElement continueButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button");
    private SelenideElement cardField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");
    private SelenideElement monthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private SelenideElement yearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private SelenideElement nameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvcField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    private SelenideElement notification = $x("//*[@id=\"root\"]/div/div[2]");
    private SelenideElement errorNotification =$x("//*[@id=\"root\"]/div/div[3]");
    private SelenideElement nameError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");
    private SelenideElement cardError =$x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    private SelenideElement monthError =$x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private SelenideElement yearError =$x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private SelenideElement cvcError =$x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");

    public DataHelper() {
    }

    public void getApprovedCard(){
        cardField.setValue("4444 4444 4444 4441");
    }

    public void getDeclinedCard(){
        cardField.setValue("4444 4444 4444 4442");
    }

    public void getMonth(int months) {
        String month = LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern("MM"));
        monthField.setValue(month);
    }

    public void getYear(int years) {
        String year = LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("yy"));
        yearField.setValue(year);
    }

    public void getValidName() {
        nameField.setValue("Olga Lebedeva");
    }

    public void getValidCVC() {
        cvcField.setValue("000");
    }

    public void clickThePaymentButton() {
        paymentButton.click();
    }

    public void clickTheCreditButton() {
        creditButton.click();
    }

    public void clickTheContinueButton() {
        continueButton.click();
    }

    public void getSuccessNotification() {
        notification.shouldHave(text("Успешно Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void getErrorNotification() {
        errorNotification.shouldHave(text("Ошибка Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void getNameErrorNotification() {
        nameError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void getCardErrorNotification() {
        cardError.shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void getMonthFormatErrorNotification() {
        monthError.shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void getYearFormatErrorNotification() {
        yearError.shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void getCVCErrorNotification() {
        cvcError.shouldHave(text("Неверный формат")).shouldBe(visible);
    }

    public void getMonthErrorNotification() {
        monthError.shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void getYearErrorNotification() {
        yearError.shouldHave(text("Неверно указан срок действия карты")).shouldBe(visible);
    }
}
