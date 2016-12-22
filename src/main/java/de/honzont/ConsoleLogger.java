package main.java.de.honzont;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Gäbeler on 22.12.2016.
 * Part of Project BlackJack
 */
public class ConsoleLogger{

    Logger logger;
    FileHandler fh = null;
    SimpleFormatter formatter;

    public ConsoleLogger() {
        logger = Logger.getLogger(ConsoleLogger.class.getName());
        formatter = new SimpleFormatter();
        logger.setUseParentHandlers(false);
        try {
            fh = new FileHandler("ConsoleOutput.log");
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.info("Logging Started");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void log(String string) {
        logger.info(string);
    }

}
