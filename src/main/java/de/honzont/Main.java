package main.java.de.honzont;

import java.util.Scanner;
/**
 * Created by JensGe on 03.11.2016.
 */
public class Main{

    public static final String JACK = "BlackJack > ";
    private static final int STANDARDBANKROLL = 200;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game blackjack = new Game();
        while (blackjack.gameIsActive) {
            String menuSelection = Game.selectGameMenuOption();
            runSelection(menuSelection, blackjack);
        }
        scanner.close();

    }
    private static void runSelection(String selection, Game game)  {
        switch (selection) {
            case "n":
                game.newRound();
                break;
            case "a":
                game.addPlayer(queryNewPlayerName(),STANDARDBANKROLL);
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
        System.out.print(JACK + "New Player Name: ");
        return scanner.nextLine();
    }
    private static Player selectPlayerToRemove(Game game) { //TODO Exit-Strategy needed
        for (int i = 1; i< game.players.size(); i++) {
            System.out.println(JACK + i + " " + game.players.get(i).getName());
        }
        System.out.print(JACK + "Remove Player with Number: ");
        Integer selection = scanner.nextInt();
        return game.players.get(selection);
    }


}

