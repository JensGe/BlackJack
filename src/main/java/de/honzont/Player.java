package main.java.de.honzont;

import java.util.ArrayList;
import java.util.List;

/**
* Created by JensGe on 03.11.2016.
*/
public class Player {
    private Integer bankroll;
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();

    private Integer bet = 0;
    private PlayerState playerState = PlayerState.ACTIVE;


    public Player(String name, Integer bankroll) {
        this.name = name;
        this.bankroll = bankroll;
    }
    public Player() {
        this.name = "Dealer";
        Boolean isDealer = true;
        this.bankroll = 0;
    }

    public void drawCard(Card card) {
        this.hand.add(card);
    }

    public Integer getHandValue() {
        Integer handValue = 0;
        Integer aceCounter = 0;
        for (Card card : hand) {
            handValue += card.getValue();
            if (card.getName().contains("Ace")) {
                aceCounter++;
            }
        }
        while (handValue > 21 && aceCounter > 0) {
            handValue -= 10;
            aceCounter--;
        }
        return handValue;
    }
    public String getHandAsString() {
        StringBuilder handStringBuilder = new StringBuilder();
        handStringBuilder.append("");
        for (int i = 0; i+1 < hand.size(); i++) {
            handStringBuilder.append(hand.get(i).getName());
            handStringBuilder.append(", ");
        }
        if (hand.size() > 0) {
            handStringBuilder.append(hand.get(hand.size()-1).getName());
        }
        return handStringBuilder.toString();
    }
    public void clearHand() {
        hand.clear();
    }

    public Integer getBankroll() {
        return bankroll;
    }
    public void setBankroll(Integer bankroll) {
        this.bankroll = bankroll;
    }
    public String getName() {
        return name;
    }
    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }
    public PlayerState getPlayerState() {
        return playerState;
    }
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

}