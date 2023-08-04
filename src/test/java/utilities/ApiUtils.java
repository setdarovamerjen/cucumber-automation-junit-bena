package utilities;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtils {
    public static String getToken(){
        RequestBody requestBody= new RequestBody();
        requestBody.setEmail(Config.getValue("tokenEmail"));
        requestBody.setPassword(Config.getValue("tokenPassword"));
        Response response= RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(Config.getValue("CashWiseApiURL")+"/api/myaccount/auth/login");
        String token= response.jsonPath().get("jwt_token");
        System.out.println("Status code of token: "+ response.statusCode());
        return token;
    }
}
