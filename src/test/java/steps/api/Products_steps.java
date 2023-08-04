package steps.api;

import io.cucumber.java.en.*;
import org.junit.Assert;
import utilities.ApiRunner;

import java.util.HashMap;
import java.util.Map;

public class Products_steps {

    @Given("user hits get all products api with {string} params page {int} and size {int}")
    public void user_hits_get_all_products_api_with_params_page_and_size(String path, int page, int size) {
        Map<String, Object> params= new HashMap<>();
        params.put("page", page);
        params.put("size", size);

        ApiRunner.runGET(path, params);

    }
    @Then("user verifies all the products service types and categories.")
    public void user_verifies_all_the_products_service_types_and_categories() {
        System.out.println(ApiRunner.getCustomResponse().getResponseBody());
        int size= ApiRunner.getCustomResponse().getResponses().size();
        for (int i=0; i<size; i++){
            Assert.assertNotNull(ApiRunner.getCustomResponse().getResponses().get(i).getService_type());
            Assert.assertNotNull(ApiRunner.getCustomResponse().getResponses().get(i).getCategory());
        }
    }

    @Then("user verifies service types to be {string} or {string}")
    public void user_verifies_service_types_to_be_or(String Service, String Product) {
       int size= ApiRunner.getCustomResponse().getResponses().size();
       for (int i=0; i<size; i++){
           if (ApiRunner.getCustomResponse().getResponses().get(i).getService_type().getService_type_id()==1){
               Assert.assertEquals(ApiRunner.getCustomResponse().getResponses().get(i).getService_type().getService_type(), Service);

           } else if (ApiRunner.getCustomResponse().getResponses().get(i).getService_type().getService_type_id()==2){
               Assert.assertEquals(ApiRunner.getCustomResponse().getResponses().get(i).getService_type().getService_type(), Product);
           }
       }

    }
}
