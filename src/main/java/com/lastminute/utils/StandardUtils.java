package com.lastminute.utils;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class StandardUtils {

    public static String generateOriginDestinationKey(String origin, String destination) {
        StringBuilder sb = new StringBuilder();
        return sb.append(origin)
                .append("_")
                .append(destination).toString();
    }

    public static String getFirstWord(String command) {
        String firstWord = "";
        if (notNullOrEmptyString(command)) {
            String[] words = command.split(" ");
            firstWord = words[0];
        }
        return firstWord;
    }


    public static  List<String> getWordsFromCommands(String command) {
        List<String> commandList = Collections.EMPTY_LIST;
        if (notNullOrEmptyString(command)) {
            String[] words = command.split(" ");
            commandList = Arrays.asList(words);
        }
        return commandList;
    }

    public static boolean notNullOrEmptyString(String text) {
        if (text != null && !text.isEmpty()) {
            return true;
        }
        return false;
    }

    public static long calculateIntervalDatesDays(Date departureDate) {
        return DAYS.between(LocalDate.now(), departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
