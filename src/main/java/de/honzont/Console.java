package main.java.de.honzont;

import java.util.Scanner;

/**
 * Created by Jensge on 11.11.2016.
 */
interface Console {

    /**
     * Standard Consoleoutput with BlackJack Prefix
     */
    static void print() {
        System.out.print("BlackJack > ");
    }

    /**
     * @param output
     */
    static void println(String output) {
        print();
        System.out.println(output);
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
