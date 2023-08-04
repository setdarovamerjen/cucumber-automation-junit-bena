package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.ApiUtils;
import utilities.Config;

public class CashwiseCategoriesTest {

    @Test
    public void createCategory() throws JsonProcessingException {
        RequestBody requestBody= new RequestBody();
        requestBody.setCategory_title("Online cunsulting");
        requestBody.setCategory_description("For cunsulting the online customers");
        requestBody.setFlag(true);
        String url=Config.getValue("CashWiseApiURL")+"/api/myaccount/categories";
        String token= ApiUtils.getToken();

        Response response= RestAssured.
                given().
                auth().
                oauth2(token).
                contentType(ContentType.JSON).
                body(requestBody).
                post(url);

        response.prettyPrint();

        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println(customResponse.getCategory_id());
        System.out.println(customResponse.getCreated());


    }
}
