package ru.netology.testMode;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(7777)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static LoginToAccountInfo generatelogin(String locale) {
        Faker faker = new Faker(new Locale(locale));
        LoginToAccountInfo account = new LoginToAccountInfo(faker.name().username(), faker.internet().password(), "active");
        return account;
    }
    public static LoginToAccountInfo generateloginblocked(String locale) {
        Faker faker = new Faker(new Locale(locale));
        LoginToAccountInfo account = new LoginToAccountInfo(faker.name().username(), faker.internet().password(), "blocked");
        return account;
    }

    public static void createAccount(LoginToAccountInfo account) {
        Gson gson = new Gson();
        given() // "дано"
                .spec(requestSpec)
                .body(gson.toJson(account))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
