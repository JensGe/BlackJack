package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.Player;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JensGe on 04.11.2016.
 */
public class PlayerTest {
    private Player testPlayer;
    private Player testDealer;
    private Card testCard;
    private Card testCard2;


    /**
     *
     */
    @Before
    public void setUp() {
        testDealer = new Player();
        testPlayer = new Player("Tester", 200);

        testCard = new Card("testcardname", 10);
        testCard2 = new Card("testcardname2", 11);
    }

    /**
     *
     */
    @Test
    public void playerTest() {
        String expectedname = "Tester";
        Integer expectedbankroll = 200;
        assertEquals(expectedname, testPlayer.getName());
        assertEquals(expectedbankroll, testPlayer.getBankroll());

        expectedname = "Dealer";
        expectedbankroll = 0;
        assertEquals(expectedname, testDealer.getName());
        assertEquals(expectedbankroll, testDealer.getBankroll());
    }

    /**
     *
     */
    @Test
    public void drawTest() {
        testPlayer.drawCard(testCard);
        assertEquals(testCard, testPlayer.getHand().get(0));
    }

    @Test
    public void getHandValueTest() {
        testPlayer.drawCard(testCard);
        testPlayer.drawCard(testCard2);
        Integer testhandValue = testCard.getValue() + testCard2.getValue();
        assertEquals(testhandValue, testPlayer.getHandValue());

    }

    @Test
    public void getHandAsStringTest() {
        testPlayer.drawCard(testCard);
        testPlayer.drawCard(testCard2);
        String testHandString = "testcardname, testcardname2";
        assertEquals(testHandString, testPlayer.getHandAsString());
    }






}
