package api;

import dev.failsafe.internal.util.Assert;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class RegresTest{
    private final static String URL = "https://reqres.in";

    @Test
    public void checkAvatarAndIdTest(){
//        Specification.installSpecification(Specification.requestSpec(URL),);
        List<UserData> users = given().
                when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.stream().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x ->x.getEmail().endsWith("@regres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));

        }
    }
}
