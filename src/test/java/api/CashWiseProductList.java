package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.ApiUtils;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashWiseProductList {

    @Test
            public void cashWise() {
        String token = ApiUtils.getToken();
        String url = Config.getValue("CashWiseApiURL") + "/api/myaccount/products";

        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("size", 2);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
    }
}
