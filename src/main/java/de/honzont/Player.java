package main.java.de.honzont;

import java.util.ArrayList;


/**
* Created by JensGe on 03.11.2016.
*/
public class Player {
    private Integer bankroll;
    private String name;
    private Boolean isDealer = false;
    private ArrayList<Card> hand = new ArrayList<>();
    private Integer bet = 0;
    private Boolean wantsMoreCards = true;


    /**
     * @param name name of a player
     * @param bankroll bankroll of a player
     */
    public Player(String name, Integer bankroll) {
        this.name = name;
        this.bankroll = bankroll;
    }

    /**
     * If no parameter (name, bankroll) is passed, a Dealer is created
     */
    public Player() {
        this.name = "Dealer";
        this.isDealer = true;
        this.bankroll = 0;
    }

    public Integer getHandValue() {
        Integer handValue = 0;
        for (Card card : hand) {
            handValue += card.getValue();
        }
        return handValue;
    }

    public String getHandAsString() {
        StringBuilder handStringBuilder = new StringBuilder();
        for (int i = 0; i < hand.size(); ++i) {
            handStringBuilder.append(hand.get(i).getName());
            handStringBuilder.append(", ");         //TODO Iterater mit has next?
        }
        return handStringBuilder.toString();
    }

    /* Standard Getter & Setter */
    public Integer getBankroll() {
        return bankroll;
    }
    public void setBankroll(Integer bankroll) {
        this.bankroll = bankroll;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getDealer() {
        return isDealer;
    }
    public void setDealer(Boolean dealer) {
        isDealer = dealer;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * @param card
     */
    public void drawCard(Card card) {
        this.hand.add(card);
    }
    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }


    public Boolean checkWantsMoreCards() {
        return wantsMoreCards;
    }

    public void setWantsMoreCards(Boolean bool) {
        wantsMoreCards = bool;
    }
}
