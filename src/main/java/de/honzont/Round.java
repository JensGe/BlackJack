package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

import static main.java.de.honzont.Main.consoleOutputLine;
import static main.java.de.honzont.Main.getIntegerInput;
import static main.java.de.honzont.Main.getStringInput;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round {
    private CardDeck deck = new CardDeck();
    private Integer playersOutOfGameCounter;
    ArrayList<Player> playersByHandValue = new ArrayList<>();

    Round(final Game game) {
        askPlayersForBet(game.players);
        deck.shuffleDeck();
        dealFirstCards(game.players);
        dealSecondCards(game.players);
        playersOutOfGameCounter = 0;
        do {
            for (int i=1; i < game.players.size(); i++) {
                runPlayerTurn(game.players.get(i));
            }
        } while (playersOutOfGameCounter +1 < game.players.size());
        runDealerTurn(game.players);
        rankNonBustedPlayers(game.players);
        setFinalPlayerStates(playersByHandValue, game.players);
        printScores(playersByHandValue);
        setBetsAndBankrolls(game.players);

        // TODO  cleanUp()

    }

    private void setBetsAndBankrolls(ArrayList<Player> players) {
        for (Player player : players) {
            switch (player.getPlayerState()) {
                case WINNER:
                    player.setBankroll(player.getBankroll() + player.getBet());
                case LOOSER:
                    player.setBankroll(player.getBankroll() - player.getBet());
            }
            player.setBet(0);
        }
    }

    private void printScores(ArrayList<Player> playersByHandValue) {
        for (Player player: playersByHandValue) {
            consoleOutputLine(player.getName() + "'s Handvalue" + player.getHandValue() + " and State: " + player.getPlayerState());
        }
    }

    private void setFinalPlayerStates(ArrayList<Player> playersByHandValue, ArrayList<Player> players) {
        PlayerState drawOrWin;
        if (checkforSingleWinner(playersByHandValue)) {
            setSingleWinner(playersByHandValue);
        } else {
            if (checkDealerHasTopHand(playersByHandValue, players)) {
                drawOrWin = PlayerState.DRAWER;
            } else {
                drawOrWin = PlayerState.WINNER;
            }
            setMultipleWinner(playersByHandValue, drawOrWin);
        }
    }



    private boolean checkforSingleWinner(ArrayList<Player> playersByHandValue) {
        if (playersByHandValue.get(0).getHandValue() > playersByHandValue.get(1).getHandValue()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDealerHasTopHand(ArrayList<Player> playersByHandValue, ArrayList<Player> players) {
        if (playersByHandValue.get(0).getHandValue().equals(players.get(0).getHandValue())) {
            return true;
        } else {
            return false;
        }
    }


    private void setSingleWinner(ArrayList<Player> playersByHandValue) {
        playersByHandValue.get(0).setPlayerState(PlayerState.WINNER);
        for (int i = 1; i < playersByHandValue.size(); i++) {
            playersByHandValue.get(i).setPlayerState(PlayerState.LOOSER);
        }
    }

    private void setMultipleWinner(ArrayList<Player> playersByHandValue, PlayerState drawOrWin) {
        for (Player player : playersByHandValue) {
            if (playersByHandValue.get(0).getHandValue().equals(player.getHandValue())) {
                player.setPlayerState(drawOrWin);
            } else {
                player.setPlayerState(PlayerState.LOOSER);
            }
        }
    }


    private void rankNonBustedPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerState() != PlayerState.BUSTED) {
                playersByHandValue.add(player);
            }
        }
        Collections.sort(playersByHandValue, new PlayerComparator() {
            public int compare(Player self, Player other) {
                return super.compare(self, other);
            }
        });

        for (Player player : playersByHandValue) {
            consoleOutputLine(player.getName() + " " + player.getHandValue());
        }
    }

    private void askPlayersForBet(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            consoleOutputLine(players.get(i).getName() + ", choose your bet: ");
            players.get(i).setBankroll(getIntegerInput());
        }
    }

    private void dealFirstCards(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Card card = deck.getCard();
            players.get(i).drawCard(card);
            String printCardName;
            if (players.get(i).getDealer()) {
                printCardName = "hidden Card";
            } else {
                printCardName = card.getName();
            }
            consoleOutputLine(players.get(i).getName() + " draws a " + printCardName);
        }
    }

    private void dealSecondCards(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Card card = deck.getCard();
            players.get(i).drawCard(card);
            consoleOutputLine(players.get(i).getName() + " draws a " + card.getName());
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
            playersOutOfGameCounter++;
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
            playersOutOfGameCounter++;
            consoleOutputLine(player.getName() + ", you are BUSTED with " + player.getHandValue());
        }
    }

    private void runDealerTurn(ArrayList<Player> players) {
        Player dealer = players.get(0);
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
