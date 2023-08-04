package steps.api;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Blackjack_stepsTest {
    Response response;
    boolean shuffled;
    String deckId;
    Map<String, Object> params= new HashMap<>();
    int remaining;


    @Given("user hits get new deck with {string}")
    public void user_hits_get_new_deck_with(String url) {
        //1st part
        String newDeckURL="https://deckofcardsapi.com/api/deck/new/";
        response= RestAssured.get(newDeckURL);
        response.prettyPrint();
        deckId=response.jsonPath().getString("deck_id");
        System.out.println(deckId);
        shuffled= response.jsonPath().getBoolean("shuffled");
        Assert.assertFalse(shuffled);
    }


    @Then("user verifies total remaining to be {int} and not shuffled")
    public void user_verifies_total_remaining_to_be_and_not_shuffled(int remaining) {
		Assert.assertEquals(response.jsonPath().getInt("remaining"), remaining);
    }


    @Then("user hits shuffle api with same deck id")
    public void user_hits_shuffle_api_with_same_deck_id() {
        String shuffleURL="https://deckofcardsapi.com/api/deck/"+deckId+"/shuffle";
        response= RestAssured.get(shuffleURL);
        response.prettyPrint();
        shuffled= response.jsonPath().getBoolean("shuffled");
    }

    @Then("user verifies for shuffled value to be {string}")
    public void user_verifies_for_shuffled_value_to_be(String shuffled) {
        Assert.assertTrue(this.shuffled);
    }

    @Then("user hits draw api two times with same deck id with count {int}")
    public void user_hits_draw_api_two_times_with_same_deck_id_with_count(int count) {
        String drawUrl="https://deckofcardsapi.com/api/deck/"+deckId+"/draw/";
        params.put("count", 3);
        response= RestAssured.given().params(params).get(drawUrl);
        System.out.println("First Player cards");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));

        response= RestAssured.given().params(params).get(drawUrl);
        // response.prettyPrint();
        System.out.println("Second Player cards");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));

    }
    @Then("user verifies remaining cards to be {int}")
    public void user_verifies_remaining_cards_to_be(int remaining) {
        this.remaining=response.jsonPath().getInt("remaining");
        Assert.assertEquals(this.remaining, remaining);

    }


}
