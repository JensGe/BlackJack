package main.java.de.honzont;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Gäbeler on 03.11.2016.
 */
class Game extends Main{
    Boolean gameIsActive;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Round> rounds = new ArrayList<>();

    Game() {
        System.out.println("BlackJack > Starting Game ...");
        gameIsActive = true;
    }

    static String selectGameMenuOption() {
        Scanner scanner = new Scanner(System.in);
        String menuSelection;
        System.out.println("BlackJack > *******************");
        System.out.println("BlackJack > * (N)ew Round     *");
        System.out.println("BlackJack > * (A)dd Player    *");
        System.out.println("BlackJack > * (R)emove Player *");
        System.out.println("BlackJack > * (S)tatistics    *");
        System.out.println("BlackJack > * (Q)uit Game     *");
        System.out.println("BlackJack > *******************");
        System.out.print("BlackJack > ");
        menuSelection = scanner.next().toLowerCase().substring(0,1);
        return menuSelection;
    }

    void newRound() {
        Round round = new Round();
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
            System.out.println("BlackJack > Player: " + player.getName() + ", Bankroll: " + player.getBankroll());
        }
    }

    void quitGame() {
        gameIsActive = false;
        System.out.println("BlackJack > Good Game, Bye");
    }

}
