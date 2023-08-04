package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class CashWiseBankAccount {

        @Test
        public void test1(){
        String token=ApiUtils.getToken();
        Response response= RestAssured.given().auth().oauth2(token).
                 get(Config.getValue("CashWiseApiURL")+"/api/myaccount/bankaccount");
        System.out.println(response.statusCode());
        response.prettyPrint();


        int size= response.jsonPath().getInt("$.size()");
        for (int i=0; i<size; i++){
            System.out.println("====================================");
            System.out.println(i+1+"===Bank account===");
            String bankAccountName=response.jsonPath().getString("["+i+"].bank_account_name");
            String accountPay=response.jsonPath().getString("["+i+"].type_of_pay");
            String balance=response.jsonPath().getString("["+i+"].balance");

            Assert.assertFalse("Bank account name is empty: "+i, bankAccountName.trim().isEmpty());
            Assert.assertFalse("Bank type is empty: "+i, accountPay.trim().isEmpty());
            Assert.assertFalse("Balance is empty: "+i, balance.trim().isEmpty());

        }


    }
}
