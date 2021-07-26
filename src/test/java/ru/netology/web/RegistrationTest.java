package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    @Test
    void shouldToBookCard() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);
        String outputDate = dateFormat.format(c.getTime());

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Челябинск");
        $$(".menu-item__control").last().click();
        form.$("[data-test-id=date] input").setValue(outputDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+71234567891");
        form.$(".checkbox__box").click();
        form.$(".button__text").click();
        $(withText("Успешно!")).should(Condition.visible, Duration.ofSeconds(15));
    }

}
