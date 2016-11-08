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
            case "1":
                game.newRound();
                break;
            case "a":
            case "2":
                game.addPlayer(queryNewPlayerName(), STANDARDBANKROLL);
                break;
            case "r":
            case "3":
                game.removePlayerIfItsNotTheDealer(selectPlayerToRemove(game));
                break;
            case "s":
            case "4":
                game.showPlayerStats();
                break;
            case "q":
            case "5":
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
        consoleOutputLine("New Player Name: ");
        return getStringLineInput();
    }

    /**
     * @param game
     * @return
     */
    private static Player selectPlayerToRemove(final Game game) {
        if (game.players.size() == 1) {
            consoleOutputLine("No Player to Remove");
            return game.players.get(0);
        }
        consoleOutputLine("0 None (Cancel)");
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutputLine(i + " " + game.players.get(i).getName());
        }
        Integer integerInput = getIntegerInput();
        if (integerInput >= 1 && integerInput < game.players.size()) {
            return game.players.get(integerInput);
        } else {
            return game.players.get(0);
        }



    }

    /**
     * @param output String to print to Console
     */
    public static void consoleOutputLine(String output) {
        System.out.println("BlackJack > " + output);
    }

    /**
     *
     */
    public static void consoleOutput() {
        System.out.print("BlackJack > ");
    }


    /**
     * @return String
     */
    static String getStringLineInput() {
        Scanner stringLineScanner = new Scanner(System.in);
        consoleOutput();
        return stringLineScanner.nextLine();
    }

    static String getStringInput() {
        Scanner stringScanner = new Scanner(System.in);
        consoleOutput();
        return stringScanner.next();
    }

    /**
     * @return an Integer
     */
    static Integer getIntegerInput() {
        Scanner integerScanner = new Scanner(System.in);
        consoleOutput();
        return integerScanner.nextInt();
    }


}

