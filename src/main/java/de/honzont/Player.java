package main.java.de.honzont;

/**
 * Created by Gäbeler on 03.11.2016.
 */
public class Player {
    private Integer bankroll;
    private String name;
    private Boolean isDealer = false;
    private Card[] hand = new Card[0];
    private Integer bet = 0;

    public Integer getHandValue() {
        Integer handValue = 0;
        /*TODO HandValue Calculation*/
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
    public Card[] getHand() {
        return hand;
    }
    public void setHand(Card[] hand) {
        this.hand = hand;
    }
    public Integer getBet() {
        return bet;
    }
    public void setBet(Integer bet) {
        this.bet = bet;
    }
}
