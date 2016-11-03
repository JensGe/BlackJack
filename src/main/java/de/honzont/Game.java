package main.java.de.honzont;

/**
 * Created by Gäbeler on 03.11.2016.
 */
public class Game {
    Boolean gameIsActive = true;
    Player[] players = new Player[0];
    Round[] rounds = new Round[0];

    public Game() {
        System.out.println("BlackJack > Starting Game ...");

    }
    public static void startMenu() {}

    public void startRound() {}

    public void addPlayer() {}

    public void removePlayer() {}

    public void showPlayerStats() {}

    public void quitGame() {
        gameIsActive = false;
        System.out.println("BlackJack > Good Game, Bye");

    }
}
