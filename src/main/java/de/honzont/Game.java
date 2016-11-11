package main.java.de.honzont;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by JensGe on 03.11.2016.
 */
class Game implements Console {
    ArrayList<Player> players = new ArrayList<>();

    private static final int STANDARDBANKROLL = 200;
    private Boolean gameIsActive;
    public LinkedList<Round> rounds = new LinkedList<>();

    Game() {
        Console.printLine("Starting Game ...");
        gameIsActive = true;
        players.add(new Player());              // Add Dealer at Position 0

        while (getGameIsActive()) {
            String menuSelection = selectGameMenuOption();
            runSelection(menuSelection);
        }

    }

    private static String selectGameMenuOption() {
        String selection = "";
        Console.printLine("*******************");
        Console.printLine("* (N)ew Round     *");
        Console.printLine("* (A)dd Player    *");
        Console.printLine("* (R)emove Player *");
        Console.printLine("* (S)tatistics    *");
        Console.printLine("* (Q)uit Game     *");
        Console.printLine("*******************");
        try {
            selection = Console.getStringLine().toLowerCase().substring(0,1);
        }
        catch (StringIndexOutOfBoundsException e) {
            Logger.getAnonymousLogger(String.valueOf(e));
            Console.printLine("No Selection");
            selection = "";
        }
        return selection;

    }
    private void runSelection(String selection) {
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
            Console.printLine("Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }
    private void quitGame() {
        setGameIsActive(false);
        Console.printLine("Good Game, Bye");
    }

    private static String queryNewPlayerName() {
        Console.printLine("New Player Name: ");
        return Console.getStringLine();
    }
    private Player selectPlayerToRemove() {
        if (players.size() == 1) {
            Console.printLine("No Player to Remove");
            return players.get(0);
        }
        Console.printLine("0 None (Cancel)");
        for (int i = 1; i < players.size(); i++) {
            Console.printLine(i + " " + players.get(i).getName());
        }
        Integer integerInput = Console.getInteger();
        if (integerInput >= 1 && integerInput < players.size()) {
            return players.get(integerInput);
        } else {
            return players.get(0);
        }
    }

    private Boolean getGameIsActive() {
        return gameIsActive;
    }
    private void setGameIsActive(Boolean gameIsActive) {
        this.gameIsActive = gameIsActive;
    }

}
