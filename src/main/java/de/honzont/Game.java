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
     * Starts the Game with
     * Set Active Variable to True
     * Add Dealer
     * Loops until Active Variable is set to False
     */
    public Game() {
        Console.println("Starting Game ...");
        loadGameMenu();
        setGameActive(true);
        createDealer();
        while (getGameActive()) {
            showGameMenu();
            runGameMenuSelection(getGameMenuSelection());
        }
    }

    private void loadGameMenu() {
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
    private void showGameMenu() {
        for (String line : gamemenu) {
            Console.println(line);
        }
    }
    private static String getGameMenuSelection() {
        String selection = "";
        try {
            selection = Console.getStringLine().toLowerCase().substring(0,1);
        }
        catch (StringIndexOutOfBoundsException e) {
            Logger.getAnonymousLogger(String.valueOf(e));
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
                Console.println("No Selection");
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
        if (player.equals(players.get(0))) {
            return;
        } else {
            players.remove(player);
        }

    }
    private void showPlayerStats() {
        for (Player player : players) {
            Console.println("Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }
    private void quitGame() {
        setGameActive(false);
        Console.println("Good Game, Bye");
    }

    private static String queryNewPlayerName() {
        Console.println("New Player Name: ");
        return Console.getStringLine();
    }
    private Player selectPlayerToRemove() {
        if (players.size() == 1) {
            Console.println("No Player to Remove");
            return players.get(0);
        }
        Console.println("0 None (Cancel)");
        for (int i = 1; i < players.size(); i++) {
            Console.println(i + " " + players.get(i).getName());
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
