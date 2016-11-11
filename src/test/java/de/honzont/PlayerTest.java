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
    @BeforeClass
    public void setUp() throws Exception {
        Game testGame = new Game();
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests if creating a Player with or without parameters results in
     * a Player with name and bankroll or a Dealer
     */
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


    @Test
    public void drawTest() {
        Player testPlayer = new Player("Tester", 200);
        Card testCard = new Card("testcardname", 10);
        testPlayer.drawCard(testCard);
        assertEquals(testCard, testPlayer.getHand().get(0));
    }

    @Test
    public void getHandValueTest() {
        Player testPlayer = new Player("Tester", 200);
        Card testCard = new Card("testcardname", 10);
        Card testCard2 = new Card("testcardname2", 11);
        testPlayer.drawCard(testCard);
        testPlayer.drawCard(testCard2);
        Integer testhandValue = testCard.getValue() + testCard2.getValue();
        assertEquals(testhandValue, testPlayer.getHandValue());

    }

    @Test
    public void getHandAsStringTest() {
        Player testPlayer = new Player("Tester", 200);
        Card testCard = new Card("testcardname", 10);
        Card testCard2 = new Card("testcardname2", 11);
        testPlayer.drawCard(testCard);
        testPlayer.drawCard(testCard2);
        String testHandString = "testcardname, testcardname2";
        assertEquals(testHandString, testPlayer.getHandAsString());
    }






}
