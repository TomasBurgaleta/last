package com.lastminute.service.console.commands;

import com.lastminute.utils.StandardUtils;

public class NoValidCommand  implements CommandInterface {

    @Override
    public void executeCommand(String command) {

        if (StandardUtils.notNullOrEmptyString(command)) {
            System.out.println("Not valid Command");
            }
    }
}
