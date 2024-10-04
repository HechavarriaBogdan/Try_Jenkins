import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Mesto1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    @DisplayName("Add a new photo")
    @Description("This test is for adding a new photo to Mesto.")
    public void addNewPhoto() {
        Mesto1 step = new Mesto1();
        given()
                .contentType(ContentType.JSON)
                .auth().oauth2(step.bearerToken)
                .body(Map.of("name", "Москва", "link", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenium.jpg"))
                .post("/api/cards")
                .then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_CREATED);
    }

    @Test
    @DisplayName("Like the first photo")
    @Description("This test is for liking the first photo on Mesto.")
    public void likeTheFirstPhoto() {
        Mesto1 step = new Mesto1();
        String photoId = step.getTheFirstPhotoId();
        step.likePhotoById(photoId);
        step.deleteLikePhotoById(photoId);
    }

}
