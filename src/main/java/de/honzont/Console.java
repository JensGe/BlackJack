package main.java.de.honzont;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

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
    static Integer getInteger(int min, int max) {
        Scanner integerScanner = new Scanner(System.in);
        Integer intInput;
        print();
        try {
            intInput = integerScanner.nextInt();
        } catch (InputMismatchException e) {
            Logger.getLogger(String.valueOf(e));
            intInput = 0;
        }
        return intInput;
    }
}
