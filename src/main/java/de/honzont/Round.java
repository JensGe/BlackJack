package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JensGe on 03.11.2016.
 */
public class Round implements Console {
    private CardDeck deck = new CardDeck();
    private Integer hitCounter;
    private ArrayList<Player> playersByHandValue = new ArrayList<>();

    /**
     * @param game
     */
    public Round(final Game game) {
        setPlayersToActive(game.players);
        askPlayersForBet(game.players);
        deck.shuffleDeck();
        dealFirstCards(game.players);
        dealSecondCards(game.players);
        hitCounter = game.players.size() - 1;
        do {
            for (int i=1; i < game.players.size(); i++) {
                runPlayerTurn(game.players.get(i));
            }
        } while (hitCounter > 0);
        runDealerTurn(game.players);
        rankNonBustedPlayers(game.players);
        setFinalPlayerStates(playersByHandValue, game.players);
        printScores(playersByHandValue);
        setBetsAndBankrolls(game.players);
    }

    public static void setPlayersToActive(ArrayList<Player> players) {
        for (Player player: players) {
            player.setPlayerState(PlayerState.ACTIVE);
        }
    }

    private void askPlayersForBet(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Console.println(players.get(i).getName() + ", choose your bet: ");
            players.get(i).setBankroll(Console.getInteger());
            }

        }

    private void dealFirstCards(ArrayList<Player> players) {
        for (Player player : players) {
            Card card = deck.getCard();
            player.drawCard(card);
            String printCardName;
            if (player.getDealer()) {
                printCardName = "hidden Card";
            } else {
                printCardName = card.getName();
            }
            Console.println(player.getName() + " draws a " + printCardName);
        }
    }
    private void dealSecondCards(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Card card = deck.getCard();
            players.get(i).drawCard(card);
            Console.println(players.get(i).getName() + " draws a " + card.getName());
        }
    }
    private void runPlayerTurn(Player player) {
        switch (player.getPlayerState()) {
            case ACTIVE:
                consoleOutputForActivePlayer(player);
                checkStay(player);
                break;
            case STAYED:
                Console.println("Player " + player.getName() + "STAYS at " + player.getHandValue());
                break;
            case BUSTED:
                Console.println("Player " + player.getName() + "is BUSTED at " + player.getHandValue());
                break;
            default:
                Console.println("Unknown State");
                break;
        }
        if (player.getPlayerState() == PlayerState.ACTIVE) {
            player.drawCard(deck.getCard());
            checkBust(player);
        } else {
            Console.println("Next Player");
        }

    }
    private void runDealerTurn(ArrayList<Player> players) {
        Player dealer = players.get(0);
        Console.println(dealer.getName() + "'s Hand: " + dealer.getHandAsString());
        Console.println(dealer.getName() + "'s Handvalue: " + dealer.getHandValue());
        while (dealer.getHandValue() < 17) {
            dealer.drawCard(deck.getCard());
        }
        checkBust(dealer);
        Console.println(dealer.getName() + "'s final Hand: " + dealer.getHandAsString());
        Console.println(dealer.getName() + "'s final Handvalue: " + dealer.getHandValue());
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
            Console.println(player.getName() + " " + player.getHandValue());
        }
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
            Console.println(player.getName() + "'s Handvalue" + player.getHandValue() + " and State: " + player.getPlayerState());
        }
    }
    private void setBetsAndBankrolls(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getPlayerState() == PlayerState.WINNER) {
                player.setBankroll(player.getBankroll() + player.getBet());

            } else if (player.getPlayerState() == PlayerState.LOOSER) {
                player.setBankroll(player.getBankroll() - player.getBet());

            }
            player.setBet(0);
        }
    }


    private void consoleOutputForActivePlayer(Player player) {
        Console.println(player.getPlayerState() + " Player " + player.getName() + " ");
        Console.println(player.getName() + ", your Hand: " + player.getHandAsString());
        Console.println("Your Handvalue is " + player.getHandValue());
        Console.println("Do you want to (h)it or (s)tay? >");
    }

    private void checkStay(Player player) {                                            //TODO Aufsplitten
        if ("s".equals(Console.getString().toLowerCase().substring(0, 1))) {
            player.setPlayerState(PlayerState.STAYED);
            hitCounter--;
        }
    }
    private void checkBust(Player player) {                                                   // TODO Aufsplitten
        if (player.getHandValue() > 21 && player.getPlayerState() == PlayerState.ACTIVE) {

            player.setPlayerState(PlayerState.BUSTED);
            hitCounter--;
            Console.println(player.getName() + ", you are BUSTED with " + player.getHandValue());
        }
    }
    private boolean checkForSingleWinner(ArrayList<Player> playersByHandValue) {
        if (playersByHandValue.size() == 1) {
            return true;
        } else return playersByHandValue.get(0).getHandValue() > playersByHandValue.get(1).getHandValue();
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
