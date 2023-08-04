package api;

import entities.CustomResponse;
import entities.RequestBody;
import org.junit.Test;
import utilities.ApiRunner;

import java.util.HashMap;
import java.util.Map;

public class SellerTestWithUtil {

    @Test
    public void getSellers(){
        String path = "/api/myaccount/sellers/1087";
        ApiRunner.runGET(path);
        System.out.println(ApiRunner.getCustomResponse().getResponseBody());
        System.out.println(ApiRunner.getCustomResponse().getSeller_name());
        System.out.println(ApiRunner.getCustomResponse().getEmail());
    }

    @Test
    public void getSellersList(){
        //TODO
        String path = "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 50);
        ApiRunner.runGET(path, params);
        int counter = 0;
        for (CustomResponse cr: ApiRunner.getCustomResponse().getResponses()){
            System.out.println(cr.getCompanyName());
            counter++;
        }
        System.out.println("total: " + counter);
    }


    @Test
    public void createNewSeller(){
        String path = "/api/myaccount/sellers";
        RequestBody body= new RequestBody();
        ApiRunner.runPOST(path, body);
        System.out.println(ApiRunner.getCustomResponse().getResponseBody());
    }

    @Test
    public void singleSellerCreation(){
//        String pathForPost="/api/myaccount/seller";
//        Faker faker= new Faker();
//        String companyName= faker.company().name();
//        String email=faker.internet().emailAddress();
//        String phone=faker.phoneNumber().phoneNumber();
//
//        RequestBody body= new RequestBody();
//        body.setCompany_name(companyName);
//        body.setEmail(email);

    }
}
