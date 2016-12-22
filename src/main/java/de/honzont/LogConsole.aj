package main.java.de.honzont;

/**
 * Created by Gäbeler on 22.12.2016.
 * Part of Project BlackJack
 */
public aspect LogConsole extends ConsoleLogger{

    ConsoleLogger consoleLogger = new ConsoleLogger();

    pointcut outputConsole() : execution(* Console.print(..));

    after() returning() : outputConsole(){
        Object[] arguments = thisJoinPoint.getArgs();
        for (int i =0; i < arguments.length; i++){
            Object argument = arguments[i];
            if (argument != null){
                consoleLogger.log(argument.toString() );
            }
        }
    }
}
