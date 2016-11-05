package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JensGe on 03.11.2016.
 */
public class CardDeck {
    ArrayList<Card> deck = new ArrayList<>();

    /**
     * Card Deck Content Creation
     *
     */
    public CardDeck() {
        deck.add(new Card("Two of Diamonds", 2));
        deck.add(new Card("Three of Diamonds", 3));
        deck.add(new Card("Four of Diamonds", 4));
        deck.add(new Card("Five of Diamonds", 5));
        deck.add(new Card("Six of Diamonds", 6));
        deck.add(new Card("Seven of Diamonds", 7));
        deck.add(new Card("Eight of Diamonds", 8));
        deck.add(new Card("Nine of Diamonds", 9));
        deck.add(new Card("Ten of Diamonds", 10));
        deck.add(new Card("Jack of Diamonds", 10));
        deck.add(new Card("Queen of Diamonds", 10));
        deck.add(new Card("King of Diamonds", 10));
        deck.add(new Card("Ace of Diamonds", 11));
        deck.add(new Card("Two of Hearts", 2));
        deck.add(new Card("Three of Hearts", 3));
        deck.add(new Card("Four of Hearts", 4));
        deck.add(new Card("Five of Hearts", 5));
        deck.add(new Card("Six of Hearts", 6));
        deck.add(new Card("Seven of Hearts", 7));
        deck.add(new Card("Eight of Hearts", 8));
        deck.add(new Card("Nine of Hearts", 9));
        deck.add(new Card("Ten of Hearts", 10));
        deck.add(new Card("Jack of Hearts", 10));
        deck.add(new Card("Queen of Hearts", 10));
        deck.add(new Card("King of Hearts", 10));
        deck.add(new Card("Ace of Hearts", 11));
        deck.add(new Card("Two of Clubs", 2));
        deck.add(new Card("Three of Clubs", 3));
        deck.add(new Card("Four of Clubs", 4));
        deck.add(new Card("Five of Clubs", 5));
        deck.add(new Card("Six of Clubs", 6));
        deck.add(new Card("Seven of Clubs", 7));
        deck.add(new Card("Eight of Clubs", 8));
        deck.add(new Card("Nine of Clubs", 9));
        deck.add(new Card("Ten of Clubs", 10));
        deck.add(new Card("Jack of Clubs", 10));
        deck.add(new Card("Queen of Clubs", 10));
        deck.add(new Card("King of Clubs", 10));
        deck.add(new Card("Ace of Clubs", 11));
        deck.add(new Card("Two of Spades", 2));
        deck.add(new Card("Three of Spades", 3));
        deck.add(new Card("Four of Spades", 4));
        deck.add(new Card("Five of Spades", 5));
        deck.add(new Card("Six of Spades", 6));
        deck.add(new Card("Seven of Spades", 7));
        deck.add(new Card("Eight of Spades", 8));
        deck.add(new Card("Nine of Spades", 9));
        deck.add(new Card("Ten of Spades", 10));
        deck.add(new Card("Jack of Spades", 10));
        deck.add(new Card("Queen of Spades", 10));
        deck.add(new Card("King of Spades", 10));
        deck.add(new Card("Ace of Spades", 11));
      }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card getCard() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
}
