package com.lastminute.service.console;

import com.lastminute.model.FlightBooking;
import com.lastminute.model.FlightOffer;
import com.lastminute.model.SearchDataBean;
import com.lastminute.service.console.commands.*;
import com.lastminute.service.purchase.Purchase;
import com.lastminute.service.purchase.PurchaseImpl;
import com.lastminute.service.search.SearchData;
import com.lastminute.service.search.SearchDataImpl;
import com.lastminute.utils.StandardUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class ConsoleManagerImpl implements ConsoleManager{

    public static final String HELP = "help";
    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    public static final String SEARCH = "search";
    public static final String EXIT = "exit";

    private Purchase purchase;
    private SearchData searchData;



    public ConsoleManagerImpl(SearchData searchData, Purchase purchase) {
        this.searchData = searchData;
        this.purchase = purchase;

    }

    @Override
    public void executeCommand(String command) {
        String firstWord = StandardUtils.getFirstWord(command);
        CommandInterface commandInterface = getCommandToExecute(firstWord);
        if (null != commandInterface) {
            commandInterface.executeCommand(command);
        }

    }

    @Override
    public boolean isContinueConsole(String command) {
        boolean flagConsole = true;
        if (EXIT.equals(command)) {
            flagConsole = false;
        }
        return flagConsole;
    }

    private CommandInterface getCommandToExecute(String commandWord){
        CommandInterface commandInterface = null;
        switch (commandWord) {
            case HELP:
                commandInterface = new Help();
                break;
            case ORIGIN:
                commandInterface = new Origin(searchData);
                break;
            case DESTINATION:
                commandInterface = new Destination(searchData);
                break;
            case SEARCH:
                commandInterface = new Search(purchase);
                break;
            case EXIT:
                commandInterface = new Exit();
                break;
            default:
                commandInterface = new NoValidCommand();
        }
        return commandInterface;
    }


}
