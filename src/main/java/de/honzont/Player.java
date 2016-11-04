package main.java.de.honzont;

import java.util.ArrayList;
/**
 * Created by Gäbeler on 03.11.2016.
 */
public class Player {
    private Integer bankroll;
    private String name;
    private Boolean isDealer = false;
    private ArrayList<Card> hand = new ArrayList<>();
    private Integer bet = 0;



    public Player(String name,Integer bankroll) {
        this.name = name;
        this.bankroll = bankroll;
    }

    public Player() {
        this.name = "Dealer";
        this.isDealer = true;
        this.bankroll = 0;
    }

    public Integer getHandValue(Card[] hand) {
        Integer handValue = 0;
        for (int i=0;i<hand.length;i++) {
            handValue += hand[i].getValue();
        }
        return handValue;
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
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }
}
