package main.java.de.honzont;

import static main.java.de.honzont.Main.consoleOutputLine;
import static main.java.de.honzont.Main.getIntegerInput;
import static main.java.de.honzont.Main.getStringInput;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round {
    private CardDeck deck = new CardDeck();
    private Integer outOfGameCounter;



    Round(final Game game) {
        askPlayersForBet(game);
        deck.shuffleDeck();
        dealFirstCards(game);
        dealSecondCards(game);
        outOfGameCounter = 0;
        do {
            for (int i=1; i < game.players.size(); i++) {
                runPlayerTurn(game.players.get(i));
            }
        } while (outOfGameCounter +1 < game.players.size());
        runDealerTurn(game);

        
     //TODO rankPlayers(); cleanUp()

    }

    private void askPlayersForBet(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            consoleOutputLine(game.players.get(i).getName() + ", choose your bet: ");
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
            consoleOutputLine(game.players.get(i).getName() + " draws a " + printCardName);
        }
    }

    private void dealSecondCards(final Game game) {
        for (int i = 1; i < game.players.size(); i++) {
            Card card = deck.getCard();
            game.players.get(i).drawCard(card);
            consoleOutputLine(game.players.get(i).getName() + " draws a " + card.getName());
        }
    }

    private void runPlayerTurn(Player player) {
        switch (player.getPlayerState()) {
            case ACTIVE:
                consoleOutputForActivePlayer(player);
                stayChecker(player);
                break;
            case STAYED:
                consoleOutputLine("Player " + player.getName() + "STAYS at " + player.getHandValue());
                break;
            case BUSTED:
                consoleOutputLine("Player " + player.getName() + "is BUSTED at " + player.getHandValue());
                break;
            default:
                consoleOutputLine("Unknown State");
                break;
        }
        if (player.getPlayerState() == PlayerState.ACTIVE) {
            player.drawCard(deck.getCard());
            bustChecker(player);
        } else {
            consoleOutputLine("Next Player");
        }

    }

    private void stayChecker(Player player) {
        if ("s".equals(getStringInput().toLowerCase().substring(0, 1))) {
            player.setPlayerState(PlayerState.STAYED);
            outOfGameCounter++;
        }
    }

    private void consoleOutputForActivePlayer(Player player) {
        consoleOutputLine(player.getPlayerState() + " Player " + player.getName() + " ");
        consoleOutputLine(player.getName() + ", your Hand: " + player.getHandAsString());
        consoleOutputLine("Your Handvalue is " + player.getHandValue());
        consoleOutputLine("Do you want to (h)it or (s)tay? >");
    }

    private void bustChecker(Player player) {
        if (player.getHandValue() > 21 && player.getPlayerState() == PlayerState.ACTIVE) {
            player.setPlayerState(PlayerState.BUSTED);
            outOfGameCounter++;
            consoleOutputLine(player.getName() + ", you are BUSTED with " + player.getHandValue());
        }
    }

    private void runDealerTurn(Game game) {
        Player dealer = game.players.get(0);
        consoleOutputLine(dealer.getName() + "'s Hand: " + dealer.getHandAsString());
        consoleOutputLine(dealer.getName() + "'s Handvalue: " + dealer.getHandValue());
        while (dealer.getHandValue() < 17) {
            dealer.drawCard(deck.getCard());
        }
        bustChecker(dealer);
        consoleOutputLine(dealer.getName() + "'s final Hand: " + dealer.getHandAsString());
        consoleOutputLine(dealer.getName() + "'s final Handvalue: " + dealer.getHandValue());
    }


}


