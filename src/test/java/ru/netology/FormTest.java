package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999");
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowWarnName() {
        open("http://localhost:9999");
        $("[data-test-id=name]").click();
        $("[data-test-id=name] input").setValue("Пётр Павлов-Поваров");
        $("[data-test-id=phone]").click();
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
