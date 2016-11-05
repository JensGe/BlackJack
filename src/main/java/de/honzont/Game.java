package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by JensGe on 03.11.2016.
 */
class Game extends Main{
    Boolean gameIsActive;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Round> rounds = new ArrayList<>();

    Game() {
        System.out.println(JACK + "Starting Game ...");
        gameIsActive = true;
        players.add(new Player());  // Adds the Dealer
        System.out.println(JACK + "Dealer created");
    }

    static String selectGameMenuOption() {
        Scanner scanner = new Scanner(System.in);
        String menuSelection;
        System.out.println(JACK + "*******************");
        System.out.println(JACK + "* (N)ew Round     *");
        System.out.println(JACK + "* (A)dd Player    *");
        System.out.println(JACK + "* (R)emove Player *");
        System.out.println(JACK + "* (S)tatistics    *");
        System.out.println(JACK + "* (Q)uit Game     *");
        System.out.println(JACK + "*******************");
        System.out.print(JACK);
        menuSelection = scanner.next().toLowerCase().substring(0,1);
        return menuSelection;
    }

    void newRound() {
        Round round = new Round(this);
        rounds.add(round);
    }

    void addPlayer(String name, Integer bankroll) {
        Player player = new Player(name, bankroll);
        players.add(player);
    }

    void removePlayer(Player player) {
        players.remove(player);
    }

    void showPlayerStats() {
        for (Player player : players) {
            System.out.println(JACK + "Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }

    void quitGame() {
        gameIsActive = false;
        System.out.println(JACK + "Good Game, Bye");
    }

}
