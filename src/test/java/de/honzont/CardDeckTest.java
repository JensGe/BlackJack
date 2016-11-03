package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.CardDeck;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Jens on 03.11.2016.
 */
public class CardDeckTest {
    @Test
    public void CardDeckSizeTest() {
        int expect = 52;
        Card[] testDeck = new Card[52];
        assertEquals(expect, testDeck.length );
    }


}
