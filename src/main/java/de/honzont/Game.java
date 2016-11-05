package main.java.de.honzont;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);
        String menuSelection;
        consoleOutput("*******************");
        consoleOutput("* (N)ew Round     *");
        consoleOutput("* (A)dd Player    *");
        consoleOutput("* (R)emove Player *");
        consoleOutput("* (S)tatistics    *");
        consoleOutput("* (Q)uit Game     *");
        consoleOutput("*******************");
        consoleOutput("");
        menuSelection = scanner.next().toLowerCase().substring(0,1);
        return menuSelection;
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
        players.remove(player);
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
