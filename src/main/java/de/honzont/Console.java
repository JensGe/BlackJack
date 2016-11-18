package main.java.de.honzont;

import java.util.Scanner;

/**
 * Created by Jensge on 11.11.2016.
 * The Console is the Interface between Game and User
 */
interface Console {



    /**
     * @param output
     */
    static void print(String output) {
        System.out.print("BlackJack > " + output + '\n');
    }

    /**
     * Standard Consoleoutput with BlackJack Prefix
     */
    static void print() {
        System.out.print("BlackJack > ");
    }


    /**
     * @return
     */
    static String getStringLine() {
        Scanner stringLineScanner = new Scanner(System.in);
        print();
        return stringLineScanner.nextLine();
    }

    /**
     * @return
     */
    static String getString() {
        Scanner stringScanner = new Scanner(System.in);
        print();
        return stringScanner.next();
    }

    /**
     * @return
     */
    static Integer getInteger() {
        Scanner integerScanner = new Scanner(System.in);
        print();
        return integerScanner.nextInt();
    }
}
