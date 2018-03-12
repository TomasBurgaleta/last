package com.lastminute.service.console;

import com.lastminute.model.FlightBooking;
import com.lastminute.model.FlightOffer;

import java.math.BigDecimal;

public class ConsolePrintHelper {

    public static String createFlightOffer(FlightBooking flightBooking) {
        int numPassengers = flightBooking.getNumPassengers();
        BigDecimal total = flightBooking.getFinalPriceByPassenger().multiply(new BigDecimal(numPassengers));
        StringBuilder sb = new StringBuilder();
        sb.append("\t * ")
           .append(flightBooking.getFlightOffer().getCode())
           .append(",  ")
           .append(total)
           .append(" ")
           .append(flightBooking.getFlightOffer().getCurrency().getSymbol());

        if (numPassengers > 1) {
            sb.append("(")
              .append(numPassengers)
              .append(" * (")
              .append(flightBooking.getPriceModifierType().getModifier())
              .append("% of ")
              .append(flightBooking.getFlightOffer().getBasePrice())
              .append("))");
        }
        sb.append("\n");

    return sb.toString();

    }
}
