package com.lastminute.service.purchase;

import com.lastminute.model.*;
import com.lastminute.service.search.SearchData;
import com.lastminute.utils.StandardUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class PurchaseImpl implements Purchase{

    private SearchData searchData;

    public PurchaseImpl(SearchData searchData){
        this.searchData = searchData ;
    }


    @Override
    public PurchaseResult getPurchase(SearchDataBean searchDataBean) {
        PurchaseResult purchaseResult = new PurchaseResult();
        String origin = searchDataBean.getOrigin();
        String destination = searchDataBean.getDestination();

        PriceModifierType priceModifier = getPriceModifier(searchDataBean.getDepartureDate());
        List<FlightOffer> flightRouteList = searchRoute(origin, destination);

        if(!searchData.getOrigins().contains(origin)) {
            purchaseResult.addError("Not Valid origin");
        }
        if(!searchData.getDestination().contains(destination)) {
            purchaseResult.addError("Not Valid destination");
        }
        if(flightRouteList.isEmpty()) {
            purchaseResult.addError("Not flights for origin: " + origin + " and destination: " + destination);
        }
        if(priceModifier == PriceModifierType.NO_PRIORITY) {
            purchaseResult.addError("Error in the departure Date");
        }
        if(searchDataBean.getNumberPassengers() < 1){
            purchaseResult.addError("Num of passenger is not correct");
        }
        if (purchaseResult.isErrorEmpty()) {
            for(FlightOffer flightOffer : flightRouteList) {
                purchaseResult.addFlightBookingList(createFlightOffer(flightOffer, priceModifier, searchDataBean.getNumberPassengers()));
            }
        }

        return purchaseResult;
     }

     private FlightBooking createFlightOffer(FlightOffer flightOffer, PriceModifierType priceModifier, int numberPassengers){
         FlightBooking flightBooking = new FlightBooking();
         flightBooking.setFlightOffer(flightOffer);
         flightBooking.setNumPassengers(numberPassengers);
         BigDecimal finalPrice = calculatePrice(flightOffer.getBasePrice(), priceModifier);
         flightBooking.setFinalPriceByPassenger(finalPrice);
         flightBooking.setPriceModifierType(priceModifier);
        return flightBooking;
     }

     private BigDecimal calculatePrice(BigDecimal basePrice, PriceModifierType priceModifier ) {
        return basePrice.multiply(new BigDecimal(priceModifier.getModifier())).divide(new BigDecimal(100));
     }


    private PriceModifierType getPriceModifier (Date departureDate) {
        long daysToDeparture = StandardUtils.calculateIntervalDatesDays(departureDate);
        return PriceModifierType.getPriceModifierTypeByDay((int) daysToDeparture);
    }


    private List<FlightOffer> searchRoute(String origin, String destination)  {
        String keySearch = StandardUtils.generateOriginDestinationKey(origin, destination);
        return searchData.getOfferRoute(keySearch);
    }



}
