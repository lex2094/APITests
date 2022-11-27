package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RegresTest{
    private final static String URL = "https://reqres.in";

    @Test
    public void checkAvatarAndIdTest(){
        List<UserData> users = given().
                when()
                .contentType(ContentType.JSON)
                .get(URL + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        System.out.println("1");
    }
}
