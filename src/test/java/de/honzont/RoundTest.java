package test.java.de.honzont;

import main.java.de.honzont.CardDeck;
import main.java.de.honzont.Player;
import main.java.de.honzont.PlayerState;
import main.java.de.honzont.Round;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Jens on 11.11.2016.
 */
public class RoundTest {
    CardDeck testdeck;
    Integer hitCounter;
    ArrayList<Player> testplayerarraylist = new ArrayList<>();


    @Before
    public void setUp(){
        testdeck = new CardDeck();
        testplayerarraylist.add(new Player());
        testplayerarraylist.add(new Player("Testplayer1", 200));
        testplayerarraylist.add(new Player("Testplayer2", 200));

    }

    /**
     * Tests method testsetPlayersToActive
     */
    @Test
    public void testsetPlayersToActive() {
        testplayerarraylist.get(0).setPlayerState(PlayerState.BUSTED);
        testplayerarraylist.get(1).setPlayerState(PlayerState.WINNER);
        testplayerarraylist.get(2).setPlayerState(PlayerState.DRAWER);
        Round.setPlayersToActive(testplayerarraylist);
        for (int i = 0; i < testplayerarraylist.size(); i++) {
            assertEquals(testplayerarraylist.get(i).getPlayerState(), PlayerState.ACTIVE );
        }

    }

    /**
     * Tests method rankNonBustedPlayersTest
     */
    @Test
    public void rankNonBustedPlayersTest() {

    }



}