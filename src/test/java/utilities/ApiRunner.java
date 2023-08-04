package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRunner {

    private static CustomResponse customResponse;
    private static CustomResponse [] myResponse;


    public static CustomResponse getCustomResponse(){
        return customResponse;
    }

    public static void runGET(String path){
            String url = Config.getValue("CashWiseApiURL") + path;
            String token = ApiUtils.getToken();
            Response response = RestAssured.given().auth().oauth2(token).get(url);
            System.out.println("Status code: " + response.statusCode());
			response.prettyPrint();
            ObjectMapper mapper = new ObjectMapper();
            try {
                customResponse = mapper.
                        readValue(response.asString(), CustomResponse.class);
            } catch (JsonProcessingException e) {
                System.out.println("This is list of objects");
                try {
                    myResponse=mapper.readValue(response.asString(), CustomResponse [].class);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        public static void runGET(String path, Map<String, Object> params){
            String url = Config.getValue("CashWiseApiURL") + path;
            String token = ApiUtils.getToken();
            Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
            System.out.println("Status code of response: " + response.statusCode());

            ObjectMapper mapper = new ObjectMapper();
            try {
                customResponse = mapper.
                        readValue(response.asString(), CustomResponse.class);
            } catch (JsonProcessingException e) {
                System.out.println("This is list of objects");
                try {
                    myResponse=mapper.readValue(response.asString(), CustomResponse [].class);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    public static void runPOST(String path, RequestBody requestBody){
        String url = Config.getValue("CashWiseApiURL") + path;
        String token = ApiUtils.getToken();
        Response response = RestAssured.given().auth().oauth2(token).
                contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("Status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.
                    readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("Probably list response");
        }
    }

    public static void runPOSTZh(String path, RequestBody requestBody){
        String url = Config.getValue("apiUrl") + path;
        String token = Config.getValue("token");
        Header header= new Header("x-auth-token", token);

        Response response = RestAssured.given().contentType("application/json").body(requestBody).header(header).post(url);
        System.out.println(url);
        System.out.println("Status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("Mapping failed");
        }
    }

    public static void runGETZh(String path){
        String url = Config.getValue("apiUrl") + path;
        String token = Config.getValue("token");
        Header header= new Header("x-auth-token", token);

        Response response = RestAssured.given().contentType("application/json").header(header).get(url);
        System.out.println(url);
        System.out.println("Status code: " + response.statusCode());


        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("Mapping failed");
        }
    }









}
