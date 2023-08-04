package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;


public class RegRest {
    public static void main(String[] args) {
        Response response= RestAssured.get("https://reqres.in/api/users/7");
        System.out.println(response.statusCode());
        response.prettyPrint();

       String email= response.jsonPath().get("data.email");
       String avatar= response.jsonPath().get("data.avatar");
       String lastName= response.jsonPath().get("data.last_name");

        Assert.assertFalse(email.isEmpty());
        Assert.assertTrue(email.endsWith("reqres.in"));




    }
}
