
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;


/*  Редактирование профиля
        •	Получить всю информацию о текущем профиле
        •	Заполнить недостающую информацию
        •	Сменить фото профиля на любую другую*/


public class EditProfiles extends BaseTest {
    Map<String, Object> responseGetProfileInfo;


    @Test
    public void editProfiles() {
        responseGetProfileInfo = getUserInfo();
        changeEmptyFields();
        getUserInfo();
        System.out.println(getUrlUploadPhoto());
        changePhotoUser();
        backUserInfo();
        getUserInfo();


    }

    public void backUserInfo() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .param("home_town", "")
                .param("status", "")
//                .queryParams(responseGetProfileInfo)
                .get(EndPoints.saveProfileInfo).andReturn();

        System.out.println(response.jsonPath().getMap("response").get("changed").toString());
    }

    public Map<String, Object> getUserInfo() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .get(EndPoints.getProfileInfo).andReturn();

        response.prettyPrint();
        return response.jsonPath().getMap("response");

    }

    public void changeEmptyFields() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .param("home_town", "Payon")
                .param("status", "Волк не тот, кто волк, а тот, кто волк")
                .get(EndPoints.saveProfileInfo).andReturn();
        response.prettyPrint();
        System.out.println(response.jsonPath().getMap("response").get("changed").toString());
    }

    public String getUrlUploadPhoto() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .param("id", "637085717")
                .get(EndPoints.saveProfilePhoto).andReturn();
        return response.jsonPath().getMap("response").get("upload_url").toString();

    }

    public void changePhotoUser() {
        String url = getUrlUploadPhoto();
        Response response = given()
                .baseUri(url)
                .contentType("multipart/form-data")
                .multiPart("file", new File("src\\main\\resources\\16114875563280.jpg"))
                .post();
        response.prettyPrint();

    }

}
