package test.java.de.honzont;

import main.java.de.honzont.Card;
import main.java.de.honzont.Player;
import main.java.de.honzont.CardDeck;
import main.java.de.honzont.Round;
import main.java.de.honzont.PlayerState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static main.java.de.honzont.Round.checkForSingleWinner;
import static main.java.de.honzont.Round.checkIfAllBusted;
import static org.junit.Assert.*;

/**
 * Created by Jens on 11.11.2016.
 */
public class RoundTest {
    CardDeck testdeck;
    Integer hitCounter;
    ArrayList<Player> testplayerarraylist = new ArrayList<>();
    private Card testCard1;
    private Card testCard2;
    private Card testCard3;
    private Card testCard4;
    private Card testCard5;

    @Before
    public void setUp(){
        testdeck = new CardDeck();
        testplayerarraylist.add(new Player());
        testplayerarraylist.add(new Player("Testplayer1", 200));
        testplayerarraylist.add(new Player("Testplayer2", 200));
        testCard1 = new Card("Four of Clubs", 4);
        testCard2 = new Card("Ace of Spades", 11);
        testCard3 = new Card("Ace of Hearts", 11);
        testCard4 = new Card("Ten of Diamonds", 10);
        testCard5 = new Card("Eight of Diamonds", 8);
    }

    @Test
    public void setPlayersToActiveTest() {
        testplayerarraylist.get(0).setPlayerState(PlayerState.BUSTED);
        testplayerarraylist.get(1).setPlayerState(PlayerState.WINNER);
        testplayerarraylist.get(2).setPlayerState(PlayerState.DRAWER);
        Round.resetPlayerState(testplayerarraylist);
        for (int i = 0; i < testplayerarraylist.size(); i++) {
            assertEquals(testplayerarraylist.get(i).getPlayerState(), PlayerState.ACTIVE );
        }

    }

    @Test
    public void countActivePlayersTest() {
        Integer expectedNumber = 2;
        Integer actualNumber = Round.countActivePlayers(testplayerarraylist);
        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void checkIfAllBustedTest() {
        testplayerarraylist.get(0).setPlayerState(PlayerState.BUSTED);
        testplayerarraylist.get(1).setPlayerState(PlayerState.BUSTED);
        testplayerarraylist.get(2).setPlayerState(PlayerState.BUSTED);
        assertEquals(true, checkIfAllBusted(testplayerarraylist));
    }

    @Test
    public void checkForSingleWinnerTest1() {
        testplayerarraylist.get(0).drawCard(testCard3);
        testplayerarraylist.get(0).drawCard(testCard4);
        testplayerarraylist.get(1).drawCard(testCard1);
        testplayerarraylist.get(1).drawCard(testCard2);
        testplayerarraylist.get(2).drawCard(testCard5);
        assertEquals(true, checkForSingleWinner(testplayerarraylist));
    }

    @Test
    public void checkForSingleWinnerTest2() {
        testplayerarraylist.get(0).drawCard(testCard2);
        testplayerarraylist.get(1).drawCard(testCard3);
        testplayerarraylist.get(2).drawCard(testCard4);
        assertEquals(false, checkForSingleWinner(testplayerarraylist));
    }

}