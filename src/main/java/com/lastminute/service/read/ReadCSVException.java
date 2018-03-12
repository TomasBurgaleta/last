package com.lastminute.service.read;

public class ReadCSVException extends Exception {

    public ReadCSVException(String message) {
        super(message);
    }

    public ReadCSVException(String message, Throwable cause) {
        super(message, cause);
    }
}
