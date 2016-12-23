package main.java.de.honzont;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConsoleLogger{

    Logger logger;
    FileHandler fh = null;
    SimpleFormatter formatter;

    public ConsoleLogger() {
        logger = Logger.getLogger(ConsoleLogger.class.getName());
        formatter = new SimpleFormatter();
        logger.setUseParentHandlers(false);
        try {
            fh = new FileHandler("C:/temp/ConsoleOutput.log");
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
