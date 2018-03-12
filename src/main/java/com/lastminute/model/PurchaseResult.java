package com.lastminute.model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseResult {

    private List<FlightBooking> flightBookingList;

    private List<String> errorList;

    public PurchaseResult() {
        errorList = new ArrayList<String>();
        flightBookingList = new ArrayList<FlightBooking>();
    }

    public void addError(String error) {
        errorList.add(error);
    }

    public boolean isErrorEmpty() {
        if (errorList.isEmpty()) {
            return true;
        }
        return false;
    }
    public List<FlightBooking> getFlightBookingList() {
        return flightBookingList;
    }

    public void addFlightBookingList(FlightBooking flightBookingList) {
        this.flightBookingList.add(flightBookingList);
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
