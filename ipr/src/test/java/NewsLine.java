import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.given;
/* Работа с лентой
        •	В ленте новостей, перейти в рекомендации
        •	Лайкнуть 5-ую запись
        •	Отправить в черный лист автора 6-й записи
        •	Добавить в закладки 2-ую запись*/


public class NewsLine extends BaseTest {
    List<Object> owner_ids;
    List<Object> item_ids;
    List<Object> texts;
    int likes;


    @Test
    public void getRecommendedNews() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .get(EndPoints.newsFeedReccom).andReturn();
        response.prettyPrint();

        owner_ids = response.jsonPath().get("response.items.source_id");
        item_ids = response.jsonPath().get("response.items.post_id");
        texts = response.jsonPath().get("response.items.text");

        getMapForLike();
    }

    public Map<String, Object> getMapForLike() {
        Map<String, Object> queryParamsForLike = new HashMap<String, Object>();

        queryParamsForLike.put("v", "5.52");
        queryParamsForLike.put("type", "post");
        queryParamsForLike.put("owner_id", owner_ids.get(4));
        queryParamsForLike.put("item_id", item_ids.get(4));
        return queryParamsForLike;
    }

    public Map<String, Object> getMapForBan() {
        Map<String, Object> queryParamsForBan = new HashMap<String, Object>();
        queryParamsForBan.put("v", "5.52");
        queryParamsForBan.put("owner_id", owner_ids.get(5));
        return queryParamsForBan;
    }

    public Map<String, Object> getMapForFave() {
        Map<String, Object> queryParamsForFave = new HashMap<String, Object>();
        queryParamsForFave.put("v", "5.52");
        queryParamsForFave.put("owner_id", owner_ids.get(1));
        queryParamsForFave.put("id", item_ids.get(1));
        return queryParamsForFave;
    }

    @Test(dependsOnMethods = {"getRecommendedNews"})
    public void addLike() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForLike())
                .get(EndPoints.addLike).andReturn();
        response.prettyPrint();
        likes = response.jsonPath().get("response.likes");
    }

    @Test(dependsOnMethods = {"addLike"})
    public void isLiked() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForLike())
                .get(EndPoints.isLiked).andReturn();
        response.prettyPrint();
    }

    @Test(dependsOnMethods = {"getRecommendedNews"})
    public void addBan() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForBan())
                .get(EndPoints.accountBan).andReturn();
        response.prettyPrint();
    }

    @Test(dependsOnMethods = {"getRecommendedNews"})
    public void addPostToFavorites() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForFave())
                .get(EndPoints.faveAddPost).andReturn();
        response.prettyPrint();
    }

    @Test(dependsOnMethods = {"addPostToFavorites"})
    public void checkPostToFavorites() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .param("v", "5.52")
                .get(EndPoints.faveGet).andReturn();
        response.prettyPrint();
    }

    public void deleteLike() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForLike())
                .get(EndPoints.deleteLike).andReturn();
        response.prettyPrint();
    }

    public void unBan() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForBan())
                .get(EndPoints.accountUnban).andReturn();
        response.prettyPrint();
    }

    public void removePostFromFavorites() {
        Response response = given()
                .baseUri("https://api.vk.com/method")
                .param("access_token", "5f0e397f109c115d4e1190550369f6968cf797f919eafd1d530080175190a57ecb4b39604325cd342b526")
                .queryParams(getMapForFave())
                .get(EndPoints.faveRemovePost).andReturn();
        response.prettyPrint();
    }

    @AfterClass
    public void cleanProperties() {
        deleteLike();
        unBan();
        removePostFromFavorites();
    }
}