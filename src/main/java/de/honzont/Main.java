package main.java.de.honzont;

import java.util.Scanner;
/**
 * Created by JensGe on 03.11.2016.
 */
public class Main {

    private static final int STANDARDBANKROLL = 200;
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Game blackjack = new Game();
        while (blackjack.getGameIsActive()) {
            String menuSelection = Game.selectGameMenuOption();
            runSelection(menuSelection, blackjack);
        }
        SCANNER.close();

    }
    private static void runSelection(final String selection, final Game game)  {
        switch (selection) {
            case "n":
                game.newRound();
                break;
            case "a":
                game.addPlayer(queryNewPlayerName(), STANDARDBANKROLL);
                break;
            case "r":
                game.removePlayer(selectPlayerToRemove(game));
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
    private static String queryNewPlayerName() {
        consoleOutput("New Player Name: ");
        return SCANNER.nextLine();
    }
    //TODO Exit-Strategy needed
    private static Player selectPlayerToRemove(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutput(i + " " + game.players.get(i).getName());
        }
        consoleOutput("Remove Player with Number: ");
        Integer selection = SCANNER.nextInt();
        return game.players.get(selection);
    }

    /**
     * @param output String to print to Console
     */
    public static void consoleOutput (String output) {
        consoleOutput(output);
    }


}

