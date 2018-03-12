package com.lastminute.model;

import java.math.BigDecimal;

public class FlightBooking {

    private BigDecimal finalPriceByPassenger;
    private int numPassengers;
    private FlightOffer flightOffer;
    private PriceModifierType priceModifierType;


    public BigDecimal getFinalPriceByPassenger() {
        return finalPriceByPassenger;
    }

    public void setFinalPriceByPassenger(BigDecimal finalPriceByPassenger) {
        this.finalPriceByPassenger = finalPriceByPassenger;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public FlightOffer getFlightOffer() {
        return flightOffer;
    }

    public void setFlightOffer(FlightOffer flightOffer) {
        this.flightOffer = flightOffer;
    }

    public PriceModifierType getPriceModifierType() {
        return priceModifierType;
    }

    public void setPriceModifierType(PriceModifierType priceModifierType) {
        this.priceModifierType = priceModifierType;
    }


}
