package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round implements Console {
    private CardDeck deck = new CardDeck();
    private ArrayList<Player> roundPlayers = new ArrayList<>();
    private RoundState closingRoundSituation;

    /**
     * @param game
     */
    public Round(final Game game) {
        prepareRound(game.players);
        runAllPlayerTurns(game.players);
        runDealerTurn(game.players.get(0));
        calculateResults(game.players);
        presentResults();
        cleanupRound();
    }


    /* 1. Round() Methods */

    private void prepareRound(ArrayList<Player> players) {
        resetPlayerState(players);
        askPlayersForBet(players);
        deck.shuffleDeck();
        dealCardToEveryone(players);
        dealCardOnlyToPlayers(players);
    }

    private void runAllPlayerTurns(ArrayList<Player> players) {
        if (countActivePlayers(players) > 1) {
            for (int i = 1; i < players.size(); i++) {
                runActivePlayerTurn(players.get(i));
            }
        }
    }

    private void runDealerTurn(Player dealer) {
        while (dealer.getHandValue() < 17) {
            dealer.drawCard(deck.getCard());
        }
        setDealerState(dealer);
        Console.print(dealer.getName() + "'s final Hand: " + dealer.getHandAsString());
        Console.print(dealer.getName() + "'s final Handvalue: " + dealer.getHandValue());
    }

    private void calculateResults(ArrayList<Player> players) {
        createNewPlayerArray(players);
        sortRoundPlayers();
        checkClosingRoundSituation(players);
        assignPlayerStates(players);
        payOut();
        clearBets();
    }

    private void presentResults() {
        Console.print("Round Results: ");
        for (Player player : roundPlayers) {
            Console.print(player.getName() + " is "
                    + player.getPlayerState() + " with "
                    + player.getHandValue() + " Points.");
        }
    }

    private void cleanupRound() {
        discardHands();
    }

    /* 1.1 prepareRound() Methods */

    public static void resetPlayerState(ArrayList<Player> players) {
        for (Player player : players) {
            player.setPlayerState(PlayerState.ACTIVE);
        }
    }

    private void askPlayersForBet(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Integer latentBet = players.get(i).getBankroll() + 1;
            while (latentBet > players.get(i).getBankroll()) {
                Console.print(players.get(i).getName() + ", choose your bet: ");
                latentBet = Console.getInteger(1, players.get(i).getBankroll());
            }
            players.get(i).setBet(latentBet);
        }
    }

    private void dealCardToEveryone(ArrayList<Player> players) {
        for (Player player : players) {
            Card card = deck.getCard();
            player.drawCard(card);
            Console.print(player.getName() + " draws a " + card.getName());
        }
    }

    private void dealCardOnlyToPlayers(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Card card = deck.getCard();
            players.get(i).drawCard(card);
            Console.print(players.get(i).getName() + " draws a " + card.getName());
        }
    }


    /* 1.2 runAllPlayerTurns Methods */

    private int countActivePlayers(ArrayList<Player> players) {
        Integer counter = 0;
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getPlayerState() == PlayerState.ACTIVE) {
                counter++;
            }
        }
        return counter;
    }

    private void runActivePlayerTurn(Player player) {
        if (player.getPlayerState() == PlayerState.ACTIVE) {
            consoleOutputForActivePlayer(player);
            if (checkStay(player)) {
                setStay(player);
            }
            player.drawCard(deck.getCard());
            if (checkBust(player)) {
                setBust(player);
            }
        }
    }


    /* 1.4 calculateResults() Methods */

    private void createNewPlayerArray(ArrayList<Player> players) {
        for (Player player : players) {
            roundPlayers.add(player);
        }
    }

    private void sortRoundPlayers() {
        Collections.sort(roundPlayers, new PlayerComparator() {
            @Override
            public int compare(Player self, Player other) {
                return super.compare(self, other);
            }
        });
    }

    private void checkClosingRoundSituation(ArrayList<Player> players) {
        if (checkIfAllBusted()) {
            closingRoundSituation = RoundState.ALLBUSTED;
        }
        if (checkForSingleWinner() && checkDealerHasTopHand(players)) {
            closingRoundSituation = RoundState.DEALERWINSALONE;
        }
        if (checkForSingleWinner() && !checkDealerHasTopHand(players)) {
            closingRoundSituation = RoundState.PLAYERWINSALONE;
        }
        if (!checkForSingleWinner() && checkDealerHasTopHand(players)) {
            closingRoundSituation = RoundState.DEALERPLAYERDRAW;
        }
        if (!checkForSingleWinner() && !checkDealerHasTopHand(players)) {
            closingRoundSituation = RoundState.MULTIPLAYERWIN;
        } else {
            closingRoundSituation = RoundState.UNKNOWN;
        }
    }

    private void assignPlayerStates(ArrayList<Player> players) {
        switch (closingRoundSituation) {
            case ALLBUSTED:
                assignAllBusted(players);
                break;
            case DEALERWINSALONE:
                assignDealerWinsAlone(players);
                break;
            case PLAYERWINSALONE:
                assignPlayerWinsAlone(players);
                break;
            case DEALERPLAYERDRAW:
                assignDealerPlayerDraw(players);
                break;
            case MULTIPLAYERWIN:
                assignMultiPlayerWin(players);
                break;
            default:
                break;
        }
    }

    private void payOut() {
        for (Player player : roundPlayers) {
            if (player.getPlayerState() == PlayerState.WINNER) {
                player.setBankroll(player.getBankroll() + player.getBet());

            } else if (player.getPlayerState() == PlayerState.LOOSER) {
                player.setBankroll(player.getBankroll() - player.getBet());
            }
        }
    }

    private void clearBets() {
        for (Player player : roundPlayers) {
            player.setBet(0);
        }
    }


    /* 1.2.2 runActivePlayerTurn() Methods */

    private void consoleOutputForActivePlayer(Player player) {
        Console.print(player.getPlayerState() + " Player " + player.getName() + " ");
        Console.print(player.getName() + ", your Hand: " + player.getHandAsString());
        Console.print("Your Handvalue is " + player.getHandValue());
        Console.print("Do you want to (h)it or (s)tay? >");
    }


    /* 1.4.3 checkClosingRoundSituation() Methods */

    private Boolean checkIfAllBusted() {
        return (roundPlayers.size() == 0);
    }

    private boolean checkForSingleWinner() {
        return roundPlayers.size() == 1 ||
                roundPlayers.get(0).getHandValue() > roundPlayers.get(1).getHandValue();
    }

    private boolean checkDealerHasTopHand(ArrayList<Player> players) {
        return roundPlayers.get(0).getHandValue().equals(players.get(0).getHandValue());
    }


    /* 1.4.4 assignPlayerStates() Methods */

    private void assignAllBusted(ArrayList<Player> players) {
        setAllPlayerStates(players, PlayerState.BUSTED);
    }

    private void assignDealerWinsAlone(ArrayList<Player> players) {
        setAllPlayerStates(players, PlayerState.LOOSER);
        players.get(0).setPlayerState(PlayerState.WINNER);
    }

    private void assignPlayerWinsAlone(ArrayList<Player> players) {
        setAllPlayerStates(players, PlayerState.LOOSER);
        roundPlayers.get(0).setPlayerState(PlayerState.WINNER);
    }

    private void assignDealerPlayerDraw(ArrayList<Player> players) {
        setAllPlayerStates(players, PlayerState.LOOSER);
        for (Player player : roundPlayers) {
            if (player.getHandValue() == roundPlayers.get(0).getHandValue())
                player.setPlayerState(PlayerState.DRAWER);
        }
    }

    private void assignMultiPlayerWin(ArrayList<Player> players) {
        setAllPlayerStates(players, PlayerState.LOOSER);
        for (Player player : roundPlayers) {
            if (player.getHandValue() == roundPlayers.get(0).getHandValue())
                player.setPlayerState(PlayerState.WINNER);
        }
    }


    private void discardHands() {
        for (Player player : roundPlayers) {
            player.clearHand();
        }
    }


    /* Checker, Getter & Setter */
    private Boolean checkStay(Player player) {
        return "s".equals(Console.getString().toLowerCase().substring(0, 1));
    }

    private void setStay(Player player) {
        player.setPlayerState(PlayerState.STAYED);
        Console.print("You stay with " + player.getHandValue());
    }

    private Boolean checkBust(Player player) {
        return (player.getHandValue() > 21);
    }

    private void setBust(Player player) {
        player.setPlayerState(PlayerState.BUSTED);
        Console.print("You are BUSTED with " + player.getHandValue());
    }

    private void setDealerState(Player dealer) {
        if (checkBust(dealer)) {
            setBust(dealer);
        } else {
            setStay(dealer);
        }
    }

    private void setAllPlayerStates(ArrayList<Player> players, PlayerState playerState) {
        for (Player player : players) {
            player.setPlayerState(playerState);
        }
    }

}

