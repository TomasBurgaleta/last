package com.lastminute.service.console.commands;

import com.lastminute.service.search.SearchData;

import java.util.List;

public class Destination implements CommandInterface{

    private SearchData searchData;

    public Destination (SearchData searchData) {
        this.searchData = searchData;
    }

    @Override
    public void executeCommand(String command) {
        System.out.println("Destination IATA Airports: ");
        List<String> destinationList = searchData.getDestination();
        for (String airport :destinationList) {
            System.out.print(airport + " ");
        }
        System.out.print("\n\n");
    }
}
