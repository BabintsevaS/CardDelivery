package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    @Test
    void shouldToBookCard() {
        LocalDate meetingDate = LocalDate.now().plusDays(3);;
        String outputDate = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Челябинск");
        form.$("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(outputDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+71234567891");
        form.$(".checkbox__box").click();
        form.$(".button__text").click();
        $(withText("Успешно!")).should(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").should(Condition.text("Встреча успешно забронирована на "+ outputDate)).should(Condition.visible);
    }

}
