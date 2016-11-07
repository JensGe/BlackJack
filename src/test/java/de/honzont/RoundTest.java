package test.java.de.honzont;

import main.java.de.honzont.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Jens on 07.11.2016.
 */
public class RoundTest {
    ArrayList<Player> players;

    @Before
    public void setup() {
        players = new ArrayList<>();
        players.add(new Player("Player1",200));
        players.add(new Player("Player2",200));
        players.add(new Player("Player3",200));
        players.add(new Player("Player4",200));

    }

    @Test
    public void RoundTest() {



    }

}