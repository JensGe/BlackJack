package main.java.de.honzont;

/**
 * Created by Gäbeler on 03.11.2016.
 */
public class CardDeck {
    private Card[] deck;

    /**
     * Card Deck Content Creation
     *
     */
    public CardDeck() {
        this.deck = new Card[52];
        this.deck[0] = new Card("Two of Diamonds", 2);
        this.deck[1] = new Card("Three of Diamonds", 3);
        this.deck[2] = new Card("Four of Diamonds", 4);
        this.deck[3] = new Card("Five of Diamonds", 5);
        this.deck[4] = new Card("Six of Diamonds", 6);
        this.deck[5] = new Card("Seven of Diamonds", 7);
        this.deck[6] = new Card("Eight of Diamonds", 8);
        this.deck[7] = new Card("Nine of Diamonds", 9);
        this.deck[8] = new Card("Ten of Diamonds", 10);
        this.deck[9] = new Card("Jack of Diamonds", 10);
        this.deck[10] = new Card("Queen of Diamonds", 10);
        this.deck[11] = new Card("King of Diamonds", 10);
        this.deck[12] = new Card("Ace of Diamonds", 11);
        this.deck[13] = new Card("Two of Hearts", 2);
        this.deck[14] = new Card("Three of Hearts", 3);
        this.deck[15] = new Card("Four of Hearts", 4);
        this.deck[16] = new Card("Five of Hearts", 5);
        this.deck[17] = new Card("Six of Hearts", 6);
        this.deck[18] = new Card("Seven of Hearts", 7);
        this.deck[19] = new Card("Eight of Hearts", 8);
        this.deck[20] = new Card("Nine of Hearts", 9);
        this.deck[21] = new Card("Ten of Hearts", 10);
        this.deck[22] = new Card("Jack of Hearts", 10);
        this.deck[23] = new Card("Queen of Hearts", 10);
        this.deck[24] = new Card("King of Hearts", 10);
        this.deck[25] = new Card("Ace of Hearts", 11);
        this.deck[26] = new Card("Two of Clubs", 2);
        this.deck[27] = new Card("Three of Clubs", 3);
        this.deck[28] = new Card("Four of Clubs", 4);
        this.deck[29] = new Card("Five of Clubs", 5);
        this.deck[30] = new Card("Six of Clubs", 6);
        this.deck[31] = new Card("Seven of Clubs", 7);
        this.deck[32] = new Card("Eight of Clubs", 8);
        this.deck[33] = new Card("Nine of Clubs", 9);
        this.deck[34] = new Card("Ten of Clubs", 10);
        this.deck[35] = new Card("Jack of Clubs", 10);
        this.deck[36] = new Card("Queen of Clubs", 10);
        this.deck[37] = new Card("King of Clubs", 10);
        this.deck[38] = new Card("Ace of Clubs", 11);
        this.deck[39] = new Card("Two of Spades", 2);
        this.deck[40] = new Card("Three of Spades", 3);
        this.deck[41] = new Card("Four of Spades", 4);
        this.deck[42] = new Card("Five of Spades", 5);
        this.deck[43] = new Card("Six of Spades", 6);
        this.deck[44] = new Card("Seven of Spades", 7);
        this.deck[45] = new Card("Eight of Spades", 8);
        this.deck[46] = new Card("Nine of Spades", 9);
        this.deck[47] = new Card("Ten of Spades", 10);
        this.deck[48] = new Card("Jack of Spades", 10);
        this.deck[49] = new Card("Queen of Spades", 10);
        this.deck[50] = new Card("King of Spades", 10);
        this.deck[51] = new Card("Ace of Spades", 11);
      }
}
