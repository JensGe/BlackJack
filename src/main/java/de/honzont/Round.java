package main.java.de.honzont;

import static main.java.de.honzont.Main.consoleOutput;
import static main.java.de.honzont.Main.getIntegerInput;
import static main.java.de.honzont.Main.getStringInput;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round {
    private CardDeck deck = new CardDeck();
    private Integer overallStayCount;


    Round(final Game game) {
        askPlayersForBet(game);
        deck.shuffleDeck();
        dealFirstCards(game);
        dealSecondCards(game);
        overallStayCount = 0;
        do {
            for (int i=1; i < game.players.size(); i++) {
                runPlayerTurn(game.players.get(i));
            }
        } while (overallStayCount+1 < game.players.size());
        consoleOutput("OverallStayCount :" + overallStayCount);


     //TODO runDealerTurn(), calculateWinners(); cleanUp()

    }

    private void askPlayersForBet(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutput(game.players.get(i).getName() + ", choose your bet: ");
            game.players.get(i).setBankroll(getIntegerInput());
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

    private void runPlayerTurn(Player player) {
        if (player.checkWantsMoreCards()) {
            consoleOutput(player.getName() + ", your Hand: " + player.getHandAsString());
            consoleOutput("Your Handvalue is " + player.getHandValue());
            consoleOutput("Do you want to (h)it or (s)tay? >");
            String tempstring = getStringInput();
            String choiceAsString = tempstring.toLowerCase().substring(0, 1);
            player.setWantsMoreCards(convertPlayerChoice(choiceAsString));
        } else {
            consoleOutput(player.getName() + " stays already");
        }

        if (player.checkWantsMoreCards()) {
            player.drawCard(deck.getCard());
        } else {
            consoleOutput("Next Player");


        /*TODO nächste Methoden programmieren
        eventuallygetCard()
        getPlayerChoice()
        checkHandValue() */
            }




    }

    private Boolean convertPlayerChoice(String selection) {
        if ("s".equals(selection)) {
            overallStayCount += 1;
            return false;
        } else {
            return true;
        }
    }
}


