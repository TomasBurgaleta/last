package com.lastminute.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class FlightOffer {

    private String code;
    private Currency currency ;
    private BigDecimal basePrice;
    private String origin;
    private String destination;



    public FlightOffer(FlightRoute flightRoute, FlightPrice flightPrice) {
        this.code = flightPrice.getCode();
        this.basePrice = flightPrice.getBasePrice();
        this.currency = flightPrice.getCurrency();
        this.origin = flightRoute.getOrigin();
        this.destination = flightRoute.getDestination();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        code = code;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
