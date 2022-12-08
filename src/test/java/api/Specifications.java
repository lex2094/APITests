package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//Спецификации rest assured - ответ, который мы ожидаем от библиотеки
public class Specifications {

        // создем заготовку и передаем ссылку в стринге
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder() //вызываем билдер
                .setBaseUri(url) //прописываем ссылку
                .setContentType(ContentType.JSON) //контент тайп джейсон
                .build(); //вызываем билд и собираем приложуху
    }
    // создаем заготовку для ответа
    public static ResponseSpecification responseSpec200(){
        return new ResponseSpecBuilder() //спецификация для ответа, вызываем метод
                .expectStatusCode(200) // ожидаем статус код 200
                .build();
    }
    public static ResponseSpecification responseSpec400(){
        return new ResponseSpecBuilder() //спецификация для ответа, вызываем метод
                .expectStatusCode(400) // ожидаем статус код 200
                .build();
    }
    //
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request; //присваиваем наш реквест
        RestAssured.responseSpecification = response; //присваиваем наш респонс
    }
}
