import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class test {

    @Test
   public void test(){
        given()
                .baseUri("http://cookiemonster.com")
                .when()
                .get("/cookies")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
