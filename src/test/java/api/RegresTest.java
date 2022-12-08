package api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class RegresTest{
    private final static String URL = "https://reqres.in";

    @Test
    // метод парсит json
    public void checkAvatarAndIdTest(){
        Specifications.installSpecification(Specifications
                .requestSpecification(URL), Specifications.responseSpec200());
        List<UserData> users = given().
                when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        //создаем
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        // перебираем список циклом for
        for (int i = 0; i < avatars.size(); i++) {
            //достаем элемент из списка i и достает точно такой же элемент
            // ассертом проверяем почту
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
        System.out.println("1");
    }

    @Test
    // мы уже создали класс успешной регистрации, теперь нужно создать класс для метода гивен и извлечь ответ этого класса

    public void successReTest(){
    Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpec200());
    Integer id = 4; //обьявлем, что мы должны получить ОР
    String token = "QpwL5tke4Pnpja7X4";
    Register user = new Register("eve.holt@reqres.in", "pistol");
    SucessRegister sucessRegister = given()
            .body(user)
            .when()
            .post("api/register") //куда мы стучимся
            .then().log().all()
            .extract().as(SucessRegister.class); //извлекаем наш класс
        Assert.assertNotNull(sucessRegister.getId());
        Assert.assertNotNull(sucessRegister.getToken());

        Assert.assertEquals(id, sucessRegister.getId());
        Assert.assertEquals(token, sucessRegister.getToken());
    }

    //устанавливаем спецификацию
    @Test
    public void unSuccessUserTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpec400());
        Register user = new Register("sydney@fife","");
        UnSuccessRegister unSuccessRegister = given()
                .body(user)
                .post("api/register") //куда мы стучимся
                .then().log().all()
                .extract().as(UnSuccessRegister.class);
        Assert.assertEquals("Missing password", unSuccessRegister.getError());
    }
}
