package com.lastminute.model;

import java.util.Comparator;

public class FlightBookingSort implements Comparator<FlightBooking>{

    @Override
    public int compare(FlightBooking o1, FlightBooking o2) {
        return o1.getFinalPriceByPassenger().compareTo(o2.getFinalPriceByPassenger());
    }
}
