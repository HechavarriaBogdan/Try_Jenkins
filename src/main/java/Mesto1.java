import io.qameta.allure.Step;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;


 public class Mesto1 {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmIwZjNiM2Q1NmMxNDAwM2Q0Nzk1MjkiLCJpYXQiOjE3MjgwNTQwMDAsImV4cCI6MTcyODY1ODgwMH0.4Rm6GFzKPQHUP5X8Lp6DxkN_oRAWru_zmLrE2EppDLA";


    // Снять лайк с фотографии по photoId
    @Step("Delete like from the photo by id")
    public void deleteLikePhotoById(String photoId) {
        given()
                .auth().oauth2(bearerToken)
                .delete("/api/cards/{photoId}/likes", photoId)
                .then().log().all().assertThat().statusCode(HttpURLConnection.HTTP_ACCEPTED); // Неверный статус код
    }

    // Лайк фотографии по photoId
    @Step("Like a photo by id")
    public void likePhotoById(String photoId) {
        given()
                .auth().oauth2(bearerToken)
                .put("/api/cards/{photoId}/likes", photoId)
                .then().log().all()
                .assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    // Получение списка фотографий и выбор первой из него
    @Step("Take the first photo from the list")
    public String getTheFirstPhotoId() {
        return given()
                .auth().oauth2(bearerToken)
                .get("/api/cards")
                .then().log().all()
                .extract().body().path("data[0]._id");
    }
}
