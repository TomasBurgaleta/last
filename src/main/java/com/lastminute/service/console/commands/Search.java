package com.lastminute.service.console.commands;

import com.lastminute.model.*;
import com.lastminute.service.console.ConsolePrintHelper;
import com.lastminute.service.purchase.Purchase;
import com.lastminute.utils.StandardUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Search implements CommandInterface {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Purchase purchase;


    public Search (Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public void executeCommand(String command) {
        System.out.println("Init searching .....");
        List<String> errorList = new ArrayList<String>();
        List<String> commandWords = StandardUtils.getWordsFromCommands(command);
        SearchFormatResult searchFormatResult = readSearchDataBean(commandWords);
        errorList.addAll(searchFormatResult.getErrorList());

        if (errorList.isEmpty()) {
            PurchaseResult purchaseResult = purchase.getPurchase(searchFormatResult.getSearchDataBean());
            errorList.addAll(purchaseResult.getErrorList());
            if (errorList.isEmpty()) {
                List<FlightBooking> flightBookingList = purchaseResult.getFlightBookingList();
                Collections.sort(flightBookingList, new FlightBookingSort());
                System.out.println("Flights: \n");
                for (FlightBooking flightBooking : flightBookingList) {
                    System.out.print(ConsolePrintHelper.createFlightOffer(flightBooking));
                }
            }
        }

        if (!errorList.isEmpty()) {
            errorListPrint(errorList);
        }

    }

    private void errorListPrint(List<String> errorList) {
        for (String error : errorList) {
            System.out.println(error);
        }
    }

    private SearchFormatResult readSearchDataBean(List<String> commandWords) {
        SearchFormatResult searchFormatResult = new SearchFormatResult();
        SearchDataBean searchDataBean = new SearchDataBean();

        if (commandWords.size() > 4) {
            searchDataBean.setOrigin(commandWords.get(1));
            searchDataBean.setDestination(commandWords.get(2));
            try {
                searchDataBean.setDepartureDate(dateFormat.parse(commandWords.get(3)));
            } catch (ParseException e) {
                searchFormatResult.addError("Error in format date");
            }
            try {
                searchDataBean.setNumberPassengers(Integer.parseInt(commandWords.get(4)));
            } catch (NumberFormatException e) {
                searchFormatResult.addError("Error number of passengers");
            }
        } else {
            searchFormatResult.addError("Error in search format, need more data to complete a search \n search [origin] [destination] [departureDate dd/mm/yyyy] [num passengers]");
        }
        searchFormatResult.setSearchDataBean(searchDataBean);
        return searchFormatResult;
    }

}
