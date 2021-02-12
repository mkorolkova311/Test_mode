package ru.netology.testMode;


import com.codeborne.selenide.Condition;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.beans.PropertyEditor;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class testModeTest {
    private LoginToAccountInfo user_pass = DataGenerator.generatelogin("en");
    private LoginToAccountInfo user_blocked = DataGenerator.generateloginblocked("en");

    @Test
void shouldLoginToAccount(){
        DataGenerator.createAccount(user_pass);
        open("http://localhost:7777/");
        $("[data-test-id=login] input").setValue(user_pass.getName());
        $("[data-test-id=password] input").setValue(user_pass.getPassword());
        $("[data-test-id=action-login]").click();
        $("[id=root]").shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldNotValidPassword() {
        DataGenerator.createAccount(user_pass);
        open("http://localhost:7777/");
        $("[data-test-id=login] input").setValue(user_pass.getName());
        String np = user_pass.getPassword() + "12";
        System.out.println(np);
        $("[data-test-id=password] input").setValue(np);
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").waitUntil(Condition.visible,5000);
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotValidLogin() {
        DataGenerator.createAccount(user_pass);
        open("http://localhost:7777/");
        String nl = user_pass.getName() + "abc";
        System.out.println(nl);
        $("[data-test-id=login] input").setValue(nl);
        $("[data-test-id=password] input").setValue(user_pass.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").waitUntil(Condition.visible,5000);
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }


    @Test
    void shouldBlockedUser(){
        DataGenerator.createAccount(user_blocked);
        open("http://localhost:7777/");
        $("[data-test-id=login] input").setValue(user_blocked.getName());
        $("[data-test-id=password] input").setValue(user_blocked.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").waitUntil(Condition.visible,5000);
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }


}
