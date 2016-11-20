package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round implements Console {
    private CardDeck deck = new CardDeck();
    private ArrayList<Player> nonBustedPlayers = new ArrayList<>();
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

/* 
        if (checkIfAllBusted(nonBustedPlayers)) {
            setFinalPlayerStates(nonBustedPlayers, game.players);
            printScores(nonBustedPlayers);
        } else {
            Console.print("Everyone Busted.");
        }
        setBetsAndBankrolls(game.players);
        discardHands(game.players); */
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
            for (int i=1; i < players.size(); i++) {
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
        listNonBustedPlayers(players);
        orderArrayList();
        closingRoundSituation = checkClosingRoundSituation(players);
        assignPlayerStates(closingRoundSituation);




    }


    /* 2.1 prepareRound() Methods */

    public void resetPlayerState(ArrayList<Player> players) {
        for (Player player: players) {
            player.setPlayerState(PlayerState.ACTIVE);
        }
    }

    private void askPlayersForBet(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Integer latentBet = players.get(i).getBankroll() + 1;
            while (latentBet > players.get(i).getBankroll()) {
                Console.print(players.get(i).getName() + ", choose your bet: ");
                latentBet = Console.getInteger(1,players.get(i).getBankroll());
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


    /* 2.2 runAllPlayerTurns Methods */

    private int countActivePlayers(ArrayList<Player> players) {
        Integer counter = 0;
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getPlayerState() == PlayerState.ACTIVE) {
                counter++;
            }
        } return counter;
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


    /* 2.4 calculateResults() Methods */

    private void listNonBustedPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerState() != PlayerState.BUSTED) {
                nonBustedPlayers.add(player);
            }
        }
    }

    private void orderArrayList() {
        Collections.sort(nonBustedPlayers, new PlayerComparator() {
            @Override
            public int compare(Player self, Player other) {
                return super.compare(self, other);
            }
        });
    }

    private RoundState checkClosingRoundSituation(ArrayList<Player> players) {
        if (checkIfAllBusted()) {
            return RoundState.ALLBUSTED;
        }
        if (checkForSingleWinner() && checkDealerHasTopHand(players)) {
            return RoundState.DEALERWINSALONE;
        }
        if (checkForSingleWinner() && !checkDealerHasTopHand(players)) {
            return RoundState.PLAYERWINSALONE;
        }
        if (!checkForSingleWinner() && checkDealerHasTopHand(players)) {
            return RoundState.DEALERPLAYERDRAW;
        }
        if (!checkForSingleWinner() && !checkDealerHasTopHand(players)) {
            return RoundState.MULTIPLAYERWIN;
        }
        else {
            return RoundState.UNKNOWN;
        }
    }

    private void assignPlayerStates(RoundState closingRoundSituation) {
        switch (closingRoundSituation) {
            case ALLBUSTED:
                assignAllBusted();
                break;
            case DEALERWINSALONE:
                assignDealerWinsAlone();
                break;
            case PLAYERWINSALONE:
                assignPlayerWinsAlone();
                break;
            case DEALERPLAYERDRAW:
                assignDealerPlayerDraw();
                break;
            case MULTIPLAYERWIN:
                assignMultiPlayerWin();
                break;
            default:
                assignUnknownState();
                break;
        }
    }


    /* 2.2.2 runActivePlayerTurn() Methods */

    private void consoleOutputForActivePlayer(Player player) {
        Console.print(player.getPlayerState() + " Player " + player.getName() + " ");
        Console.print(player.getName() + ", your Hand: " + player.getHandAsString());
        Console.print("Your Handvalue is " + player.getHandValue());
        Console.print("Do you want to (h)it or (s)tay? >");
    }


    /* 2.4.3 checkClosingRoundSituation() Methods */

    private Boolean checkIfAllBusted() {
        return (nonBustedPlayers.size()==0);
    }

    private boolean checkForSingleWinner() {
        return nonBustedPlayers.size() == 1 ||
                nonBustedPlayers.get(0).getHandValue() > nonBustedPlayers.get(1).getHandValue();
    }

    private boolean checkDealerHasTopHand(ArrayList<Player> players) {
        return nonBustedPlayers.get(0).getHandValue().equals(players.get(0).getHandValue());
    }

    /* 2.4.4 assignPlayerStates() Methods */





    private void setFinalPlayerStates(ArrayList<Player> playersByHandValue, ArrayList<Player> players) {
        PlayerState drawOrWin;
        if (checkForSingleWinner(playersByHandValue)) {
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
    private void printScores(ArrayList<Player> playersByHandValue) {
        for (Player player: playersByHandValue) {
            Console.print(player.getName() + "'s Handvalue " + player.getHandValue() + " and State: " + player.getPlayerState());
        }
    }
    private void setBetsAndBankrolls(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerState() == PlayerState.WINNER) {
                player.setBankroll(player.getBankroll() + player.getBet());

            } else if (player.getPlayerState() == PlayerState.LOOSER || player.getPlayerState() == PlayerState.BUSTED) {
                player.setBankroll(player.getBankroll() - player.getBet());

            }
            player.setBet(0);
        }
    }
    public static void discardHands(ArrayList<Player> players) {
        for (Player player : players) {
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
}
