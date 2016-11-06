package test.java.de.honzont;

import main.java.de.honzont.Player;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JensGe on 04.11.2016.
 */
public class PlayerTest {

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
