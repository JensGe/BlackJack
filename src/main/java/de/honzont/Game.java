package main.java.de.honzont;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Created by JensGe on 03.11.2016.
 */
class Game extends Main {
    private Boolean gameIsActive;
    ArrayList<Player> players = new ArrayList<>();
    LinkedList<Round> rounds = new LinkedList<>();

    Game() {
        consoleOutput("Starting Game ...");
        gameIsActive = true;
        players.add(new Player());  // Adds the Dealer
        consoleOutput("Dealer created");
    }

    static String selectGameMenuOption() {
        consoleOutput("*******************");
        consoleOutput("* (N)ew Round     *");
        consoleOutput("* (A)dd Player    *");
        consoleOutput("* (R)emove Player *");
        consoleOutput("* (S)tatistics    *");
        consoleOutput("* (Q)uit Game     *");
        consoleOutput("*******************");
        return getStringLineInput().toLowerCase().substring(0,1);
    }

    void newRound() {
        Round round = new Round(this);
        rounds.add(round);
    }

    void addPlayer(String name, Integer bankroll) {
        Player player = new Player(name, bankroll);
        players.add(player);
    }

    void removePlayer(Player player) {
        if (player.equals(players.get(0))) {
            return;
        } else {
            players.remove(player);
        }

    }

    void showPlayerStats() {
        for (Player player : players) {
            consoleOutput("Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }

    void quitGame() {
        setGameIsActive(false);
        consoleOutput("Good Game, Bye");
    }

    public Boolean getGameIsActive() {
        return gameIsActive;
    }

    public void setGameIsActive(final Boolean gameIsActive) {
        this.gameIsActive = gameIsActive;
    }
}
