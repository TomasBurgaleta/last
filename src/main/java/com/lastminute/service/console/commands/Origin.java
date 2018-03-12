package com.lastminute.service.console.commands;

import com.lastminute.service.search.SearchData;

import java.util.List;

public class Origin implements CommandInterface {

    private SearchData searchData;

    public Origin (SearchData searchData) {
        this.searchData = searchData;
    }

    @Override
    public void executeCommand(String command) {
        System.out.println("Origin IATA Airports: ");
        List<String> originList = searchData.getOrigins();
        for (String airport :originList) {
            System.out.print(airport + " ");
        }
        System.out.print("\n\n");
    }
}
