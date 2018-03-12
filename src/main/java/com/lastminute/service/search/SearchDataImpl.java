package com.lastminute.service.search;

import com.lastminute.model.FlightOffer;
import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import com.lastminute.service.read.ReadFile;
import com.lastminute.service.read.ReadFlightPrice;
import com.lastminute.service.read.ReadFlightRoutes;

import java.util.*;
import java.util.logging.Logger;


public class SearchDataImpl implements SearchData {

    private final static Logger LOG = Logger.getLogger(SearchDataImpl.class.getName());
    private SearchDataSingleton searchData = null;


    private SearchDataSingleton getSearchDataSingleton() {
        if (this.searchData == null) {
            searchData = SearchDataSingleton.getInstance();
            ReadFile<FlightPrice> readFlightPrice = new ReadFlightPrice();
            ReadFile<FlightRoute> readFlightRoutes = new ReadFlightRoutes();
            DataSearchGenerator dataSearchGenerator = new DataSearchGenerator(searchData, readFlightPrice, readFlightRoutes);
            dataSearchGenerator.readFiles();
        }
        return searchData;
    }

    @Override
    public List<FlightOffer> getOfferRoute(String key) {
        List<FlightOffer> flightRouteList = new ArrayList<FlightOffer>();
        if (getSearchDataSingleton().getSearchData().containsKey(key)) {
            flightRouteList.addAll(getSearchDataSingleton().getSearchData().get(key));
        }
        return flightRouteList;
    }
    @Override
    public List<String> getOrigins() {
        List<String> originList = new ArrayList<String>();
        originList.addAll(getSearchDataSingleton().getOriginData());
        return originList;
    }

    @Override
    public List<String> getDestination() {
        List<String> destinationList = new ArrayList<String>();
        destinationList.addAll(getSearchDataSingleton().getDestinationData());
        return destinationList;
    }
}
