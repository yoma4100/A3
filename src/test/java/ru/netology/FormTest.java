package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @BeforeEach
    public void openSite() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendForm() {
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowWarnNameIncorrect() {
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Пётр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowWarnNameEmpty() {
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowWarnPhone() {
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowWarnPhoneEmpty() {
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowWarnCheckbox() {
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $(".button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
