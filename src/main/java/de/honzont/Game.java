package main.java.de.honzont;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Game implements Console {
    ArrayList<Player> players = new ArrayList<>();
    private static final int STANDARDBANKROLL = 200;
    private Boolean gameActive;
    private LinkedList<Round> rounds = new LinkedList<>();
    private static LinkedList<String> gamemenu = new LinkedList<>();

    /**
     * Constructor for a new Game
     */
    public Game() {
        Console.print("Starting Game ...");
        createGameMenu();
        setGameActive(true);
        createDealer();
        while (getGameActive()) {
            printGameMenu();
            runGameMenuSelection(getGameMenuSelection());
        }
    }

    private void createGameMenu() {
        gamemenu.add("*******************");
        gamemenu.add("* (N)ew Round     *");
        gamemenu.add("* (A)dd Player    *");
        gamemenu.add("* (R)emove Player *");
        gamemenu.add("* (S)tatistics    *");
        gamemenu.add("* (Q)uit Game     *");
        gamemenu.add("*******************");
    }
    private void createDealer() {
        players.add(new Player());
    }
    private void printGameMenu() {
        for (String line : gamemenu) {
            Console.print(line);
        }
    }
    private static String getGameMenuSelection() {
        String selection = "";
        try {
            selection = Console.getStringLine().toLowerCase().substring(0,1);
        }
        catch (StringIndexOutOfBoundsException e) {
            Logger.getLogger(String.valueOf(e));
            selection = "";
        }
        return selection;

    }
    private void runGameMenuSelection(String selection) {
        switch (selection) {
            case "n":
                newRound();
                break;
            case "a":
                addPlayer(queryNewPlayerName(), STANDARDBANKROLL);
                break;
            case "r":

                removePlayerIfItsNotTheDealer(selectPlayerToRemove());
                break;
            case "s":
                showPlayerStats();
                break;
            case "q":
                quitGame();
                break;
            default:
                Console.print("No Selection");
                break;
        }
    }

    private void newRound() {
        Round round = new Round(this);
        rounds.add(round);
    }
    private void addPlayer(String name, Integer bankroll) {
        Player player = new Player(name, bankroll);
        players.add(player);
    }
    private void removePlayerIfItsNotTheDealer(Player player) {
        if (!player.equals(players.get(0))) {
            players.remove(player);
        }

    }
    private void showPlayerStats() {
        for (Player player : players) {
            Console.print("Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }
    private void quitGame() {
        setGameActive(false);
        Console.print("Good Game, Bye");
    }

    private static String queryNewPlayerName() {
        Console.print("New Player Name: ");
        return Console.getStringLine();
    }
    private Player selectPlayerToRemove() {
        if (players.size() == 1) {
            Console.print("No Player to Remove");
            return players.get(0);
        }
        Console.print("0 None (Cancel)");
        for (int i = 1; i < players.size(); i++) {
            Console.print(i + " " + players.get(i).getName());
        }
        Integer integerInput = Console.getInteger();
        if (integerInput >= 1 && integerInput < players.size()) {
            return players.get(integerInput);
        } else {
            return players.get(0);
        }
    }

    private Boolean getGameActive() {
        return gameActive;
    }
    private void setGameActive(Boolean gameActive) {
        this.gameActive = gameActive;
    }

}
