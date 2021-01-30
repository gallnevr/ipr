package Hooks;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class Before {
    @BeforeMethod
    public void configureRestAssured() {
        RestAssured.basePath = "https://api.vk.com/method";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(basePath)
                .addFormParam("access_token", "0011af2fb781024bc5f6429c933da4a02aa60e26d57d4a5a8738e8bfc76739f9643ed4bf33dc7c69637b8")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        // Ожидаем положительный ответ
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }
}
