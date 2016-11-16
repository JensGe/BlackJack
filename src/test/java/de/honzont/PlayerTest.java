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
    private Card testCard1;
    private Card testCard2;
    private Card testCard3;
    private Card testCard4;
    private Card testCard5;


    /**
     * Creating testPlayer, testDealer and two TestCards
     */
    @Before
    public void setUp() {
        testDealer = new Player();
        testPlayer = new Player("Tester", 200);

        testCard1 = new Card("Four of Clubs", 4);
        testCard2 = new Card("Ace of Spades", 11);
        testCard3 = new Card("Ace of Hearts", 11);
        testCard4 = new Card("Ten of Diamonds", 10);
        testCard5 = new Card("Eight of Diamonds", 8);
    }

    /**
     * Test Generating Player and Dealer
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
     * Test if drawCard()
     */
    @Test
    public void drawTest() {
        testPlayer.drawCard(testCard1);
        assertEquals(testCard1, testPlayer.getHand().get(0));
    }

    @Test
    public void getHandValueTest() {
        testPlayer.drawCard(testCard1);
        testPlayer.drawCard(testCard2);
        Integer testhandValue = testCard1.getValue() + testCard2.getValue();
        assertEquals(testhandValue, testPlayer.getHandValue());

        testPlayer.drawCard(testCard3);
        testhandValue = testCard1.getValue() + testCard2.getValue() + testCard3.getValue() - 10;
        assertEquals(testhandValue, testPlayer.getHandValue());

        testPlayer.drawCard(testCard4);
        testhandValue = testCard1.getValue() + testCard2.getValue() - 10
                + testCard3.getValue() - 10 + testCard4.getValue();
        assertEquals(testhandValue, testPlayer.getHandValue());

        testPlayer.drawCard(testCard5);
        testhandValue = testCard1.getValue() + testCard2.getValue() - 10
                + testCard3.getValue() - 10 + testCard4.getValue()
                + testCard5.getValue();
        assertEquals(testhandValue, testPlayer.getHandValue());
        }


    @Test
    public void getHandAsStringTest() {
        testPlayer.drawCard(testCard1);
        testPlayer.drawCard(testCard2);
        String testHandString = "Four of Clubs, Ace of Spades";
        assertEquals(testHandString, testPlayer.getHandAsString());
    }






}
