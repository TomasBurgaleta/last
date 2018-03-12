package com.lastminute.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class FlightPrice {

    private String Code;
    private Currency currency = Currency.getInstance(Locale.FRANCE);
    private BigDecimal basePrice;

    public FlightPrice(String code) {
        Code = code;
    }

    public FlightPrice() {
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightPrice that = (FlightPrice) o;
        return Objects.equals(Code, that.Code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Code);
    }
}
