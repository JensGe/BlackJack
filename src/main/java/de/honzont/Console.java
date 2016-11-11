package main.java.de.honzont;

import java.util.Scanner;

/**
 * Created by Jensge on 11.11.2016.
 */
interface Console {

    static void printLine(String output) {
        System.out.println("BlackJack > " + output);
    }


    static void print() {
        System.out.print("BlackJack > ");
    }


    static String getStringLine() {
        Scanner stringLineScanner = new Scanner(System.in);
        print();
        return stringLineScanner.nextLine();
    }

    static String getString() {
        Scanner stringScanner = new Scanner(System.in);
        print();
        return stringScanner.next();
    }

    static Integer getInteger() {
        Scanner integerScanner = new Scanner(System.in);
        print();
        return integerScanner.nextInt();
    }
}
