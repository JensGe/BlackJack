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
    private Boolean isOnStay = false;



    public Player(String name,Integer bankroll) {
        this.name = name;
        this.bankroll = bankroll;
    }

    public Player() {
        this.name = "Dealer";
        this.isDealer = true;
        this.bankroll = 0;
        System.out.println("BlackJack > Dealer created");
    }

    public Integer getHandValue() {
        Integer handValue = 0;
        for (Card card : hand) {
            handValue += card.getValue();
        }
        return handValue;
    }

    public String getHandAsString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.getName() + ", ";
        }
        return handString;
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
    public void drawCard(Card card) {
        this.hand.add(card);
    }
    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }


    public Boolean getIsOnStay() {
        return isOnStay;
    }

    public void setIsOnStay(Boolean onStay) {
        isOnStay = onStay;
    }
}
