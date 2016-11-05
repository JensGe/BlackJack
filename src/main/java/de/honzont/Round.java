package main.java.de.honzont;

import java.util.Scanner;
import static main.java.de.honzont.Main.consoleOutput;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round {
    private CardDeck deck = new CardDeck();
    private final Scanner scanner = new Scanner(System.in);
    private Integer stayCounter;


    Round(final Game game) {
        askForBets(game);
        deck.shuffleDeck();
        dealFirstCards(game);
        dealSecondCards(game);
        stayCounter = 0;
        do {
            for (int i=1; i < game.players.size(); i++) {
                game.players.get(i).getHandAsString();
            }
        } while (stayCounter < game.players.size());

        runPlayerTurns(game);
     //TODO runDealerTurn(), calculateWinners(); cleanUp()

    }

    private void askForBets(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutput(game.players.get(i).getName() + ", choose your bet: ");
            game.players.get(i).setBankroll(scanner.nextInt());
        }
    }

    private void dealFirstCards(final Game game) {
        for (int i = 0; i < game.players.size(); i++) {
            Card card = deck.getCard();
            game.players.get(i).drawCard(card);
            String printCardName;
            if (game.players.get(i).getDealer()) {
                printCardName = "hidden Card";
            } else {
                printCardName = card.getName();
            }
            consoleOutput(game.players.get(i).getName() + " draws a " + printCardName);
        }
    }

    private void dealSecondCards(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            Card card = deck.getCard();
            game.players.get(i).drawCard(card);
            consoleOutput(game.players.get(i).getName() + " draws a " + card.getName());
        }
    }

    private void runPlayerTurns(final Game game) {

        for (int i = 1; i < game.players.size(); i++) {
            if (game.players.get(i).getIsOnStay()) {    //TODO getIsOnStay refactoren & true/false umdrehen
                consoleOutput(game.players.get(i).getName() + " stays already");
            } else {
                consoleOutput(game.players.get(i).getName() + ", your Hand: " + game.players.get(i).getHandAsString());
                consoleOutput("Your Handvalue is " + game.players.get(i).getHandValue());
                consoleOutput("Do you want to (h)it or (s)tay? >");
                game.players.get(i).setIsOnStay(convertPlayerChoice(scanner.next().toLowerCase().substring(0, 1)));


                /*TODO nächste Methoden programmieren
                eventuallygetCard()
                getPlayerChoice()
                checkHandValue() */
            }

        }



    }

    private Boolean convertPlayerChoice(String selection) {
        if ("s".equals(selection)) {
            stayCounter += 1;
            return false;
        } else {
            return true;
        }
    }
}


