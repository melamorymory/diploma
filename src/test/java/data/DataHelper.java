package data;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DataHelper {

    private SelenideElement paymentButton = $$(".button").find(exactText("Купить"));
    private SelenideElement creditButton = $$(".button").find(exactText("Купить в кредит"));
    private SelenideElement continueButton = $$(".button").find(text("Продолжить"));
//    private SelenideElement cardField = $x("//*[@id=\"root\"]/div/form/fieldset/*/span/span/span/following-sibling::span[position()='1']/input");
    private SelenideElement cardField = $(".form-field:first-child .input__control");
    private SelenideElement monthField = $(".form-field:nth-child(2) .input-group__input-case:first-child .input__control");
    private SelenideElement yearField = $(".form-field:nth-child(2) .input-group__input-case:last-child .input__control");
    private SelenideElement nameField = $(".form-field:nth-child(3) .input-group__input-case:first-child .input__control");
    private SelenideElement cvcField = $(".form-field:nth-child(3) .input-group__input-case:last-child .input__control");
    private SelenideElement notification = $$(".notification").find(text("Успешно Операция одобрена Банком."));
    private SelenideElement errorNotification = $$(".notification").find(text("Ошибка Ошибка! Банк отказал в проведении операции."));
    private SelenideElement nameError = $(".form-field:nth-child(3) .input-group__input-case:first-child .input__sub");
    private SelenideElement cardError = $(".form-field:first-child .input__sub");
    private SelenideElement monthError = $(".form-field:nth-child(2) .input-group__input-case:first-child .input__sub");
    private SelenideElement yearError = $(".form-field:nth-child(2) .input-group__input-case:last-child .input__sub");
    private SelenideElement cvcError = $(".form-field:nth-child(3) .input-group__input-case:last-child .input__sub");

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
