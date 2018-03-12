package com.lastminute.service.search;

import com.lastminute.model.FlightOffer;
import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.service.read.ReadFile;
import com.lastminute.utils.StandardUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSearchGenerator {

    private final static Logger LOG = Logger.getLogger(DataSearchGenerator.class.getName());
    private ReadFile<FlightPrice> readFlightPrice;
    private ReadFile<FlightRoute> readFlightRoutes;
    private SearchDataSingleton searchData;

    public DataSearchGenerator(SearchDataSingleton searchData, ReadFile<FlightPrice> readFlightPrice,
                               ReadFile<FlightRoute> readFlightRoutes) {
        this.searchData = searchData;
        this.readFlightPrice = readFlightPrice;
        this.readFlightRoutes = readFlightRoutes;
    }
    protected void readFiles() {
        try {
            if (!searchData.isCreateDataFlag()) {
                Map<String, FlightPrice> flightPriceMap = readFlightPrice.getDataBeanMap();
                Map<String, FlightRoute> flightRoutesMap = readFlightRoutes.getDataBeanMap();

                completeSearchData(flightRoutesMap, flightPriceMap);
                generateOriginAndDestination(flightRoutesMap);
                //LOG.log(Level.INFO,"Create Data for the search");
            }
            searchData.setCreateDataFlag(true);
        } catch (ReadCSVException e) {
            //LOG.log(Level.SEVERE,e.getMessage(),e);
            searchData.setCreateDataFlag(false);
        } catch (Exception e) {
            //LOG.log(Level.SEVERE,e.getMessage(),e);
            searchData.setCreateDataFlag(false);
        }

    }

    private void completeSearchData(Map<String, FlightRoute> flightRoutesMap, Map<String, FlightPrice> flightPriceMap) {

        for (String code :flightRoutesMap.keySet()) {
            if (flightPriceMap.containsKey(code)) {
                if (flightRoutesMap.containsKey(code) && flightPriceMap.containsKey(code)) {
                    FlightRoute flightRoute  = flightRoutesMap.get(code);
                    FlightPrice flightPrice = flightPriceMap.get(code);
                    FlightOffer flightOffer = new FlightOffer(flightRoute, flightPrice);
                    addOfferToList(flightOffer);
                }
            }
        }
        //LOG.log(Level.INFO,"rutas totales: " + flightRoutesMap.size());
    }

    private void addOfferToList(FlightOffer flightOffer) {
        String offerKey = generateOriginDestinationKey(flightOffer);
        Map<String, List<FlightOffer>> searchDataMap = searchData.getSearchData();
        if (!searchDataMap.containsKey(offerKey)) {
            List<FlightOffer> offersList = new ArrayList<FlightOffer>();
            searchDataMap.put(offerKey, offersList);
        }
        searchDataMap.get(offerKey).add(flightOffer);
    }


    public static String generateOriginDestinationKey(FlightOffer flightOffer) {
        return StandardUtils.generateOriginDestinationKey(flightOffer.getOrigin(), flightOffer.getDestination());
    }

    private void generateOriginAndDestination(Map<String, FlightRoute> flightRoutesMap) {
        Set<String> originSet = searchData.getOriginData();
        Set<String> destinationSet = searchData.getDestinationData();
        for (FlightRoute flightRoute: flightRoutesMap.values()) {
            originSet.add(flightRoute.getOrigin());
            destinationSet.add(flightRoute.getDestination());
        }
        //LOG.log(Level.INFO,"origins : " + originSet.size());
        //LOG.log(Level.INFO,"destinations : " + destinationSet.size());

    }
}
