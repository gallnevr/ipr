
import io.restassured.response.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class test {
    Map<String, Object> responseGetProfileInfo;

  Logger logger = LoggerFactory.getLogger(test.class);

    @Test
   public void test(){
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "0011af2fb781024bc5f6429c933da4a02aa60e26d57d4a5a8738e8bfc76739f9643ed4bf33dc7c69637b8")
                .param("v", "5.52")
                .get("/account.getProfileInfo").andReturn();

        response.prettyPrint();
        responseGetProfileInfo = response.jsonPath().getMap("response");
        logger.info(responseGetProfileInfo.toString());
//        logger.info(response.toString());


    }

}
