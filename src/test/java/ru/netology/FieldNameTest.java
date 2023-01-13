package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FieldNameTest {

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999");
        SelenideElement formName = $(".form");
        formName.$("[data-test-id=name]").click();
        formName.$("[data-test-id=name] input").setValue("Петр Павлов-Поваров");
        formName.$("[data-test-id=phone]").click();
        formName.$("[data-test-id=phone] input").setValue("+79012345678");
        formName.$("[data-test-id=agreement]").click();
        formName.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowWarnName() {
        open("http://localhost:9999");
        SelenideElement formName = $(".form");
        formName.$("[data-test-id=name]").click();
        formName.$("[data-test-id=name] input").setValue("Пётр Павлов-Поваров");
        formName.$("[data-test-id=phone]").click();
        formName.$("[data-test-id=phone] input").setValue("+79012345678");
        formName.$("[data-test-id=agreement]").click();
        formName.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
