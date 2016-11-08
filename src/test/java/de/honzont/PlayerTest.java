package test.java.de.honzont;

import main.java.de.honzont.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JensGe on 04.11.2016.
 */
public class PlayerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getHandValue() throws Exception {

    }

    @Test
    public void getHandAsString() throws Exception {

    }

    @Test
    public void getBankroll() throws Exception {

    }

    @Test
    public void setBankroll() throws Exception {

    }

    @Test
    public void getName() throws Exception {

    }

    @Test
    public void setName() throws Exception {

    }

    @Test
    public void getDealer() throws Exception {

    }

    @Test
    public void setDealer() throws Exception {

    }

    @Test
    public void getHand() throws Exception {

    }

    @Test
    public void drawCard() throws Exception {

    }

    @Test
    public void getBet() throws Exception {

    }

    @Test
    public void setBet() throws Exception {

    }

    @Test
    public void getPlayerState() throws Exception {

    }

    @Test
    public void setPlayerState() throws Exception {

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

}
