package main.java.de.honzont;

/**
 * Created by JensGe on 03.11.2016.
 */

public class Main {

    private Main() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        new Game();
    }
}
