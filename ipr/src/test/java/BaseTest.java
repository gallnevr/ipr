import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.basePath;

public abstract class BaseTest {
//    @BeforeTest
    public void configureRestAssured() {
        RestAssured.basePath = "https://api.vk.com/method";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(basePath)
                .addFormParam("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .addFormParam("v", "5.52")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .build();

        // Ожидаем положительный ответ
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }
}


