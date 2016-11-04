package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.Player;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Jens on 04.11.2016.
 */
public class PlayerTest {

    @Test
    public void playerTest() {
        Player testPlayer = new Player("Tester", 200);
        String expectedname = "Tester";
        Integer expectedbankroll = 200;
        assertEquals(expectedname,testPlayer.getName());
        assertEquals(expectedbankroll,testPlayer.getBankroll());

        Player testDealer = new Player();
        expectedname = "Dealer";
        expectedbankroll = 0;
        assertEquals(expectedname,testDealer.getName());
        assertEquals(expectedbankroll,testDealer.getBankroll());
    }

    //    @Test
//    public void getHandValueTest() {
//        int expectedHandValue = 21;
//        Card[] hand = new Card[2];
//        hand[0] = new Card("Ten of Diamonds", 10);
//        hand[1] = new Card("Ace of Diamonds", 11);
//        int returnedHandValue = getHandValue(hand);
//        assertEquals(expectedHandValue, getHandValueTest());
//    }


}
