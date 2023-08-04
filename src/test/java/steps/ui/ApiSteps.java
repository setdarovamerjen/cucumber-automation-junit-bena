package steps.ui;

import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ApiRunner;

import java.util.Optional;

public class ApiSteps {

    @Given("user hits {string} post API creation of note")
    public void user_hits_post_api_creation_of_note(String path) {
        RequestBody requestBody= new RequestBody();
        requestBody.setTitle("title");
        requestBody.setDescription("description");
        requestBody.setCategory("Home");

        ApiRunner.runPOSTZh(path, requestBody);
        System.out.println(ApiRunner.getCustomResponse().getResponseBody());

    }
    @Then("HTTP status code must be {int}")
    public void http_status_code_must_be(int status) {
        Assert.assertEquals(status,  ApiRunner.getCustomResponse().getStatus());
    }

    @Given("user hits {string} get list API notes")
    public void userHitsGetListAPINotes(String path) {
        ApiRunner.runGETZh(path);
        System.out.println(ApiRunner.getCustomResponse().getResponseBody());

    }
}
