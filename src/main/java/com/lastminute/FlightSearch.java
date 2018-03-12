package com.lastminute;


import com.lastminute.service.console.ConsoleManager;
import com.lastminute.service.console.ConsoleManagerImpl;
import com.lastminute.service.purchase.Purchase;
import com.lastminute.service.purchase.PurchaseImpl;
import com.lastminute.service.search.SearchData;
import com.lastminute.service.search.SearchDataImpl;

import java.io.Console;

public class FlightSearch {

    private static ConsoleManagerImpl consoleManager;

    public static void main(String [] args) {

        System.setProperty("file.encoding", "UTF-8");
        boolean continueConsole= true;

        while(continueConsole) {
            Console console = System.console();
            if (console != null) {
                initConsole();
                while(continueConsole) {
                    String command = console.readLine("Command: ");
                    getConsoleManager().executeCommand(command);
                    continueConsole =  getConsoleManager().isContinueConsole(command);
                }
            }
        }
        System.exit(0);

    }

    private static void initConsole() {
        System.out.print("Welcome console to search flights\n");
        System.out.print("Write one command or help\n");
    }

    private static ConsoleManager getConsoleManager() {
        if (consoleManager == null) {
            SearchData searchData = new SearchDataImpl();
            Purchase purchase = new PurchaseImpl(searchData);
            consoleManager = new ConsoleManagerImpl(searchData, purchase);
        }
        return consoleManager;
    }

}
