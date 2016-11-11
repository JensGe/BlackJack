package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.CardDeck;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        testdeck.getCard();

    }

}