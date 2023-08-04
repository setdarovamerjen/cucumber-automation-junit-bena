package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiUtils;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashWiseSeller {
    Faker faker= new Faker();

    @Test
    public void getAllSellers() throws JsonProcessingException {
        String token= ApiUtils.getToken();
        String url= Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers";

        Map <String, Object> params= new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 100);
        Response response= RestAssured.given().auth().oauth2(token).params(params).get(url);

        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        int size= customResponse.getResponses().size();
        System.out.println(size);

        for (int i=0; i<size; i++){
            System.out.println(customResponse.getResponses().get(i).getEmail());
        }
        Assert.assertEquals(response.statusCode(), 200);


    }

    @Test
    public void getSingleSeller(){
        int id=85;
        String token= ApiUtils.getToken();
        String url= Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers/"+id;

        Response response= RestAssured.given().auth().oauth2(token).get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void createSeller() throws JsonProcessingException {
        RequestBody requestBody=new RequestBody();
        String email=faker.internet().emailAddress();
        requestBody.setEmail(email);

        String companyName=faker.company().name();
        requestBody.setCompany_name(companyName);

        String sellerName=faker.name().name();
        requestBody.setSeller_name(sellerName);

        String phoneNumber= faker.phoneNumber().cellPhone();
        requestBody.setPhone_number(phoneNumber);

        String address= faker.address().fullAddress();
        requestBody.setAddress(address);

        String url=Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers/";
        String token= ApiUtils.getToken();
        Response response1=RestAssured.
                given().
                auth().
                oauth2(token).
                contentType(ContentType.JSON).
                body(requestBody).
                post(url);
        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response1.asString(), CustomResponse.class);

        //===============================================================

        Response response2= RestAssured.
                given().
                auth().
                oauth2(token).
                get(url+"/"+customResponse.getSeller_id());

        ObjectMapper mapper2=new ObjectMapper();
        CustomResponse customResponse2=mapper.readValue(response1.asString(), CustomResponse.class);

        Assert.assertEquals(email, customResponse2.getEmail());
        System.out.println(email);
        Assert.assertEquals(companyName, customResponse2.getCompanyName());
        System.out.println(companyName);
        Assert.assertEquals(sellerName, customResponse2.getSeller_name());
        System.out.println(sellerName);
        Assert.assertEquals(phoneNumber,customResponse2.getPhone_number());
        System.out.println(phoneNumber);
        Assert.assertEquals(address, customResponse2.getAddress());
        System.out.println(address);

    }
    @Test
    public void createManySellers(){
        String url=Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers";
        String token= ApiUtils.getToken();
        for (int i=0; i<50; i++){
            RequestBody requestBody= new RequestBody();
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setAddress(faker.address().fullAddress());
            Response response=RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
            Assert.assertEquals(response.statusCode(), 200);
        }
    }
    @Test
    public void deleteSellers() throws JsonProcessingException {
        String url=Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers";
        //1-get token
        String token= ApiUtils.getToken();
        //2- run Get all sellers
        Map <String, Object> params= new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 100);
        Response response= RestAssured.given().auth().oauth2(token).params(params).get(url);
        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);

        //3-get size of sellers
        System.out.println("Before delete");
        int size= customResponse.getResponses().size();
        System.out.println(size);
        //
        String url2=Config.getValue("CashWiseApiURL")+ "/api/myaccount/sellers/archive/unarchive";
        //4-Run a loop
        for (int i=0; i<size; i++){
            //5-Read the emails of each seller
            //6-Put condition ends with hotmail

            if (customResponse.getResponses().get(i).getEmail().endsWith("yahoo.com")){
                System.out.println(customResponse.getResponses().get(i).getEmail());
                int id=customResponse.getResponses().get(i).getSeller_id();
                Integer [] arr= {id};
                //8-Hit the delete API
                Map <String, Object> params2= new HashMap<>();
                params2.put("sellersIdsForArchive", id);
                params2.put("archive", true);
                Response response2=RestAssured.given().auth().oauth2(token).params(params2).post(url2);
              //  Assert.assertEquals(response2.statusCode(), 200);
            }

        }




    }

}
