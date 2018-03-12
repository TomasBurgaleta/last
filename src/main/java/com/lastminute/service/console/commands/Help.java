package com.lastminute.service.console.commands;

public class Help implements CommandInterface{

    @Override
    public void executeCommand(String command) {
        System.out.println("help");
        System.out.println("origin");
        System.out.println("destination");
        System.out.println("search [origin] [destination] [departureDate dd/mm/yyyy] [num passengers]");
        System.out.println("exit");
    }
}
