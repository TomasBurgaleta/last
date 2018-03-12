package com.lastminute.service.search;

import com.lastminute.model.FlightOffer;

import java.util.List;

public interface SearchData {
    List<FlightOffer> getOfferRoute(String key);

    List<String> getOrigins();

    List<String> getDestination();
}
