package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CardGame {

    @Test
    public void blackJack() {
        //1st part
        String newDeckURL = "https://deckofcardsapi.com/api/deck/new/";

        Response response = RestAssured.get(newDeckURL);
        response.prettyPrint();
        String deckId = response.jsonPath().getString("deck_id");
        System.out.println(deckId);

        boolean shuffled = response.jsonPath().getBoolean("shuffled");
        Assert.assertFalse(shuffled);

        //2nd part
        String shuffleURL = "https://deckofcardsapi.com/api/deck/" + deckId + "/shuffle";
        response = RestAssured.get(shuffleURL);
        response.prettyPrint();

        shuffled = response.jsonPath().getBoolean("shuffled");
        Assert.assertTrue(shuffled);

        //3rd part

        String drawUrl = "https://deckofcardsapi.com/api/deck/" + deckId + "/draw/";
        Map<String, Object> params = new HashMap<>();
        params.put("count", 3);

        int player1 = 0;
        int player2 = 0;

        for (int round = 1; round <= 2; round++) {
            response = RestAssured.given().params(params).get(drawUrl);
            System.out.print(round + "--player has: ");
            System.out.print(response.jsonPath().getString("cards[0].value") + " + ");
            System.out.print(response.jsonPath().getString("cards[1].value") + " + ");
            System.out.print(response.jsonPath().getString("cards[2].value"));
            System.out.println();

            if (response.jsonPath().getInt("remaining") == 49) {
                for (int i = 0; i <= 2; i++) {
                    if (StringUtils.isNumeric(response.jsonPath().getString("cards[" + i + "].value"))) {
                        player1 = player1 + Integer.parseInt(response.jsonPath().getString("cards[" + i + "].value"));
                    } else {
                        player1 += 10;
                    }
                }
            } else if (response.jsonPath().getInt("remaining") == 46) {
                for (int i = 0; i <= 2; i++) {
                    if (StringUtils.isNumeric(response.jsonPath().getString("cards[" + i + "].value"))) {
                        player2 = player2 + Integer.parseInt(response.jsonPath().getString("cards[" + i + "].value"));
                    } else {
                        player2 += 10;
                    }
                }
            } else {
                System.out.println("Remaining is not working BUG here");
                Assert.fail();
            }
        }

        if (player1 == player2) {
            if (player1 > 21) {
                System.out.println("Both failed");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            } else if (player1 == 21) {
                System.out.println("<<<<<<BOTH PLAYERS ARE BLACKJACK>>>>>>>");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            } else {
                System.out.println("Both WINNER");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            }
        } else {
            if (player1 > 21 && player2 > 21) {
                System.out.println("Both failed");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            } else if (player1 < 21 && player2 < 21) {
                 if (player1>player2){
                     System.out.println("Player 1 WINNER");
                     System.out.println("Player1: " + player1);
                     System.out.println("Player2: " + player2);
                 }else {
                     System.out.println("Player 2 WINNER");
                     System.out.println("Player1: " + player1);
                     System.out.println("Player2: " + player2);
                 }
            } else if (player1>21 && player2<21) {
                System.out.println("Player 2 WINNER");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            } else if (player2>21 && player1<21) {
                System.out.println("Player 1 WINNER");
                System.out.println("Player1: " + player1);
                System.out.println("Player2: " + player2);
            } else {
                if (player1 == 21) {
                    System.out.println("Player 1 is BLACKJACK");
                    System.out.println("Player1: " + player1);
                    System.out.println("Player2: " + player2);
                } else {
                    System.out.println("Player 2 is BLACKJACK");
                    System.out.println("Player1: " + player1);
                    System.out.println("Player2: " + player2);
                }
            }
        }
    }
    @Test
    public void t() {
    }


}
