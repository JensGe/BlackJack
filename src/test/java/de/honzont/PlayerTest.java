package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.Player;
import main.java.de.honzont.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JensGe on 04.11.2016.
 */
public class PlayerTest {
    private Player testplayer;
    private Player testdealer;
    private Card testCard;
    private Card testCard2;


    @Before
    public void setUp() {
        testdealer = new Player();
        testplayer = new Player("Tester", 200);

        testCard = new Card("testcardname", 10);
        testCard2 = new Card("testcardname2", 11);
    }

    @Test
    public void playerTest() {
        String expectedname = "Tester";
        Integer expectedbankroll = 200;
        assertEquals(expectedname,testplayer.getName());
        assertEquals(expectedbankroll,testplayer.getBankroll());

        expectedname = "Dealer";
        expectedbankroll = 0;
        assertEquals(expectedname,testdealer.getName());
        assertEquals(expectedbankroll,testdealer.getBankroll());
    }

    @Test
    public void drawTest() {
        testplayer.drawCard(testCard);
        assertEquals(testCard, testplayer.getHand().get(0));
    }

    @Test
    public void getHandValueTest() {
        testplayer.drawCard(testCard);
        testplayer.drawCard(testCard2);
        Integer testhandValue = testCard.getValue() + testCard2.getValue();
        assertEquals(testhandValue, testplayer.getHandValue());

    }

    @Test
    public void getHandAsStringTest() {
        testplayer.drawCard(testCard);
        testplayer.drawCard(testCard2);
        String testHandString = "testcardname, testcardname2";
        assertEquals(testHandString, testplayer.getHandAsString());
    }






}
