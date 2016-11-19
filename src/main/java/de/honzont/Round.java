package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round implements Console {
    private CardDeck deck = new CardDeck();
    private Integer outOfPlayCounter = 0;
    private ArrayList<Player> playersByHandValue = new ArrayList<>();

    /**
     * @param game
     */
    public Round(final Game game) {
        setPlayersToActive(game.players);
        askPlayersForBet(game.players);
        deck.shuffleDeck();
        dealCardToEveryone(game.players);
        dealCardOnlyToPlayers(game.players);
        do {
            for (int i=1; i < game.players.size(); i++) {
                runPlayerTurn(game.players.get(i));
            }
        } while (outOfPlayCounter < game.players.size() - 1);
        runDealerTurn(game.players);
        rankNonBustedPlayers(game.players);
        if (checkIfAllBusted(playersByHandValue)) {
            setFinalPlayerStates(playersByHandValue, game.players);
            printScores(playersByHandValue);
        } else {
            Console.print("Everyone Busted.");
        }
        setBetsAndBankrolls(game.players);
        discardHands(game.players);
    }

    public static void setPlayersToActive(ArrayList<Player> players) {
        for (Player player: players) {
            player.setPlayerState(PlayerState.ACTIVE);
        }
    }

    private void askPlayersForBet(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Integer latentBet = players.get(i).getBankroll() + 1;
            while (latentBet > players.get(i).getBankroll()) {
                Console.print(players.get(i).getName() + ", choose your bet: ");
                latentBet = Console.getInteger();
                if (latentBet > players.get(i).getBankroll()) {
                    Console.print("No Valid Choice");
                    }
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


    private void runPlayerTurn(Player player) {
        switch (player.getPlayerState()) {
            case ACTIVE:
                consoleOutputForActivePlayer(player);
                checkStay(player);
                break;
            case STAYED:
                Console.print("Player " + player.getName() + "STAYS at " + player.getHandValue());
                break;
            case BUSTED:
                Console.print("Player " + player.getName() + "is BUSTED at " + player.getHandValue());
                break;
            default:
                Console.print("Unknown State");
                break;
        }
        if (player.getPlayerState() == PlayerState.ACTIVE) {
            player.drawCard(deck.getCard());
            if (checkBust(player)) {
                setBust(player);
            };
        } else {
            Console.print("Next Player");
        }

    }
    private void runDealerTurn(ArrayList<Player> players) {
        Player dealer = players.get(0);
        Console.print(dealer.getName() + "'s Hand: " + dealer.getHandAsString());
        Console.print(dealer.getName() + "'s Handvalue: " + dealer.getHandValue());
        while (dealer.getHandValue() < 17) {
            dealer.drawCard(deck.getCard());
        }
        if (checkBust(dealer)) {
            setBust(dealer);
        };
        Console.print(dealer.getName() + "'s final Hand: " + dealer.getHandAsString());
        Console.print(dealer.getName() + "'s final Handvalue: " + dealer.getHandValue());
    }
    public void rankNonBustedPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerState() != PlayerState.BUSTED) {
                playersByHandValue.add(player);
            }
        }
        Collections.sort(playersByHandValue, new PlayerComparator() {
            @Override
            public int compare(Player self, Player other) {
                return super.compare(self, other);
            }
        });

        for (Player player : playersByHandValue) {
            Console.print(player.getName() + " " + player.getHandValue());
        }
    }
    private Boolean checkIfAllBusted(ArrayList<Player> players) {
        return (players.size()>0);
    }
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

    private void consoleOutputForActivePlayer(Player player) {
        Console.print(player.getPlayerState() + " Player " + player.getName() + " ");
        Console.print(player.getName() + ", your Hand: " + player.getHandAsString());
        Console.print("Your Handvalue is " + player.getHandValue());
        Console.print("Do you want to (h)it or (s)tay? >");
    }

    private void checkStay(Player player) {
        if ("s".equals(Console.getString().toLowerCase().substring(0, 1))) {
            player.setPlayerState(PlayerState.STAYED);
            outOfPlayCounter++;
        }
    }
    private Boolean checkBust(Player player) {
        return (player.getHandValue() > 21);
    }

    private void setBust(Player player) {
        player.setPlayerState(PlayerState.BUSTED);
        outOfPlayCounter++;
        Console.print("You are BUSTED with " + player.getHandValue());
    }



    private boolean checkForSingleWinner(ArrayList<Player> playersByHandValue) {
        return playersByHandValue.size() == 1 ||
                playersByHandValue.get(0).getHandValue() > playersByHandValue.get(1).getHandValue();
    }
    private boolean checkDealerHasTopHand(ArrayList<Player> playersByHandValue, ArrayList<Player> players) {
        return playersByHandValue.get(0).getHandValue().equals(players.get(0).getHandValue());
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
