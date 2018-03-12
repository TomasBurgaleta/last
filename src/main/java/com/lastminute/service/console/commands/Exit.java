package com.lastminute.service.console.commands;

public class Exit implements CommandInterface {
    @Override
    public void executeCommand(String command) {
        System.out.println("Exit console");
    }
}
