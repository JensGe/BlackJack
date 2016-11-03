package main.java.de.honzont;

public class Main {

    public static void main(String[] args) {
        Game blackjack = new Game();
        while (blackjack.gameIsActive) {
            blackjack.startMenu();
        }

    }
}
