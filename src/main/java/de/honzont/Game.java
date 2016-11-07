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
        consoleOutputLine("Starting Game ...");
        gameIsActive = true;
        players.add(new Player());  // Adds the Dealer
        consoleOutputLine("Dealer created");
    }

    static String selectGameMenuOption() {
        consoleOutputLine("*******************");
        consoleOutputLine("* (N)ew Round     *");
        consoleOutputLine("* (A)dd Player    *");
        consoleOutputLine("* (R)emove Player *");
        consoleOutputLine("* (S)tatistics    *");
        consoleOutputLine("* (Q)uit Game     *");
        consoleOutputLine("*******************");
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

    void removePlayerIfItsNotTheDealer(Player player) {
        if (player.equals(players.get(0))) {
            return;
        } else {
            players.remove(player);
        }

    }

    void showPlayerStats() {
        for (Player player : players) {
            consoleOutputLine("Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }

    void quitGame() {
        setGameIsActive(false);
        consoleOutputLine("Good Game, Bye");
    }

    public Boolean getGameIsActive() {
        return gameIsActive;
    }

    public void setGameIsActive(final Boolean gameIsActive) {
        this.gameIsActive = gameIsActive;
    }
}
