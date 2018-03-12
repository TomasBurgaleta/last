package com.lastminute.service.read;

import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;

import java.math.BigDecimal;
import java.util.List;

public class FactoryReadData {

    private static final int  CODE_POSITION_CODE = 0;
    private static final int  PRICE_POSITION = 1;

    private static final int  ORIGIN_POSITION = 0;
    private static final int  DESTINATION_POSITION = 1;
    private static final int  CODE_POSITION_ROUTE = 2;


    public static FlightPrice createFlightCodeByList(List<String> listBean) throws NumberFormatException {

        FlightPrice flightPrice = new FlightPrice();
        if (listBean.size() >= 2) {
            flightPrice.setCode(listBean.get(CODE_POSITION_CODE));
            flightPrice.setBasePrice(new BigDecimal(listBean.get(PRICE_POSITION)));
        }
        return flightPrice;
    }

    public static FlightRoute createFlightRouteByList(List<String> listBean){
        FlightRoute flightRoute = new FlightRoute();
        if (listBean.size() >= 3) {
            flightRoute.setOrigin(listBean.get(ORIGIN_POSITION));
            flightRoute.setDestination(listBean.get(DESTINATION_POSITION));
            flightRoute.setCode((listBean.get(CODE_POSITION_ROUTE)));
        }
        return flightRoute;
    }
}
