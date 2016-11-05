package main.java.de.honzont;

import java.util.Scanner;
import static main.java.de.honzont.Main.JACK;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round {
    private CardDeck deck = new CardDeck();
    private final Scanner scanner = new Scanner(System.in);
    private Integer stayCounter;

    Round(Game game) {
        askForBets(game);
        deck.shuffleDeck();
        dealFirstCards(game);
        dealSecondCards(game);
        runPlayerTurns(game);
     /*TODO runDealerTurn();
        calculateWinners();
        cleanUp();*/

    }

    private void askForBets(Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            System.out.print(JACK + game.players.get(i).getName() + ", choose your bet: ");
            game.players.get(i).setBankroll(scanner.nextInt());
        }
    }

    private void dealFirstCards(Game game) {
        for (int i = 0; i < game.players.size(); i++) {
            Card card = deck.getCard();
            game.players.get(i).drawCard(card);
            String printCardName;
            if (game.players.get(i).getDealer()) {
                printCardName = "hidden Card";
            } else {
                printCardName = card.getName();
            }
            System.out.println(JACK + game.players.get(i).getName() + " draws a " + printCardName);
        }
    }

    private void dealSecondCards(Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            Card card = deck.getCard();
            game.players.get(i).drawCard(card);
            System.out.println(JACK + game.players.get(i).getName() + " draws a " + card.getName());
        }
    }

    private void runPlayerTurns(Game game) {
        stayCounter = 1;
        do {
            for (int i = 1; i < game.players.size(); i++) {
                if (game.players.get(i).getIsOnStay()) {    //TODO getIsOnStay refactoren & true/false umdrehen
                    System.out.println(JACK + game.players.get(i).getName() + " stays already");
                } else {
                    System.out.println(JACK + game.players.get(i).getName() + ", your Hand: " + game.players.get(i).getHandAsString());
                    System.out.println(JACK + "Your Handvalue is " + game.players.get(i).getHandValue());
                    System.out.print(JACK + "Do you want to (h)it or (s)tay? >");
                    game.players.get(i).setIsOnStay(convertPlayerChoice(scanner.next().toLowerCase().substring(0, 1)));
                    /*TODO nächste Methoden programmieren
                    eventuallygetCard()
                    getPlayerChoice()
                    checkHandValue() */
                }

            }
        } while (stayCounter < game.players.size());


    }

    private Boolean convertPlayerChoice(String selection) {
        switch (selection) {
            case "h":
                return true;
            case "s":
                stayCounter += 1;
                return false;
            default:
                return null;
        }
    }
}


