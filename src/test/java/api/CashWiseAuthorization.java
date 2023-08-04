package api;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CashWiseAuthorization {
    public static void main(String[] args) {
        RequestBody requestBody= new RequestBody();
        requestBody.setEmail("merjen@gmail.com");
        requestBody.setPassword("merjen123");
        Response response= RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("https://backend.cashwise.us/api/myaccount/auth/login");
        System.out.println(response.statusCode());
        String token= response.jsonPath().get("jwt_token");
        System.out.println(token);


    }
}
