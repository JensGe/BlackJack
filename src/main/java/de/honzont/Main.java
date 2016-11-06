package main.java.de.honzont;

import java.util.Scanner;
/**
 * Created by JensGe on 03.11.2016.
 */
public class Main {

    private static final int STANDARDBANKROLL = 200;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Game blackjack = new Game();
        while (blackjack.getGameIsActive()) {
            String menuSelection = Game.selectGameMenuOption();
            runSelection(menuSelection, blackjack);
        }

    }

    /**
     * @param selection
     * @param game
     */
    private static void runSelection(final String selection, final Game game)  {
        switch (selection) {
            case "n":
                game.newRound();
                break;
            case "a":
                game.addPlayer(queryNewPlayerName(), STANDARDBANKROLL);
                break;
            case "r":
                game.removePlayer(selectPlayerToRemove(game));              //TODO Case: no Player to remove
                break;
            case "s":
                game.showPlayerStats();
                break;
            case "q":
                game.quitGame();
                break;
            default:
                break;
        }

    }

    /**
     * @return a Name for a new Player
     */
    private static String queryNewPlayerName() {
        consoleOutput("New Player Name: ");
        return getStringLineInput();
    }

    /**
     * @param game
     * @return
     */
    private static Player selectPlayerToRemove(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutput(i + " " + game.players.get(i).getName());
        }
        Integer playerID = getIntegerInput();
        if (playerID >= 1 && playerID < game.players.size()) {
            return game.players.get(playerID);
        } else {
            return game.players.get(0);
        }


    }

    /**
     * @param output String to print to Console
     */
    public static void consoleOutput(String output) {
        System.out.println("BlackJack > " + output);
    }


    /**
     * @return String
     */
    static String getStringLineInput() {
        Scanner stringLineScanner = new Scanner(System.in);
        System.out.print("BlackJack > ");
        return stringLineScanner.nextLine();
    }

    static String getStringInput() {
        Scanner stringScanner = new Scanner(System.in);
        System.out.print("BlackJack > ");
        return stringScanner.next();
    }

    /**
     * @return an Integer
     */
    static Integer getIntegerInput() {
        Scanner integerScanner = new Scanner(System.in);
        System.out.print("BlackJack > ");
        return integerScanner.nextInt();
    }


}

