package test.java.de.honzont;

import main.java.de.honzont.CardDeck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jens on 11.11.2016.
 */
public class CardDeckTest {


    @Test
    public void getCardTest() {
        CardDeck testdeck = new CardDeck();
        assertEquals("Two of Diamonds", testdeck.getCard().getName());
        for (int i = 0; i < 50; i++) {
            testdeck.getCard();
        }
        assertEquals("Ace of Spades", testdeck.getCard().getName());

    }

}