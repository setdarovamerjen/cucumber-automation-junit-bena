package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.CustomResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiUtils;
import utilities.Config;

public class CashWiseBankAccountTests {
    @Test
    public void getAllAccounts() throws JsonProcessingException {
        String token= ApiUtils.getToken();
        String url= Config.getValue("CashWiseApiURL") + "/api/myaccount/bankaccount";
        Response response= RestAssured.given().auth().oauth2(token).get(url);
       // response.prettyPrint();

        ObjectMapper mapper= new ObjectMapper();
        CustomResponse[] customResponse= mapper.readValue(response.asString(), CustomResponse[].class);

        System.out.print("Before deleting size of Bank accounts: ");
        System.out.println(customResponse.length);
        if (customResponse.length>0) {
            for (int i = 0; i < customResponse.length; i++) {
                if (customResponse[i].getBalance() < 1000) {
                    System.out.println("Inside if else condition");
                    String id = customResponse[i].getId();
                    System.out.println(customResponse[i].getBank_account_name());
                    Response response1 = RestAssured.given().auth().oauth2(token).delete(url + "/" + id);
                    Assert.assertEquals(response1.statusCode(), 200);
                    System.out.println("==============================================================");
                }else {
                    System.out.println("All accounts balance is more than 1000");
                }
            }
        }else {
            System.out.println("No accounts found");
            Assert.fail();
        }




    }
}
