package com.lastminute.service.read;

import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import com.lastminute.utils.StandardUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ReadFlightRoutes extends ReadFileAbstract<FlightRoute> {

    private static final String CVS_FILE = "flight-routes.csv";

    public ReadFlightRoutes() {
        super(CVS_FILE);
    }


//    @Override
//    public Map<String, FlightRoute> getDataBeanMap() throws  ReadCSVException {
//        List<FlightRoute> listData = getAllData();
//        Map<String, FlightRoute> flightRouteMap = new HashMap<String, FlightRoute>();
//        for (FlightRoute flightRoute : listData){
//            String code = flightRoute.getCode();
//            if (StandardUtils.notNullOrEmptyString(code)) {
//                flightRouteMap.put(code, flightRoute);
//            }
//        }
//        return flightRouteMap;
//    }
    //TODO: use lambda
    @Override
    public Map<String, FlightRoute> getDataBeanMap() throws ReadCSVException {
        List<FlightRoute> listData = getAllData();
        Map<String, FlightRoute> cvsDataBeanMap =
                listData.stream().filter(u -> u.getCode() != null).collect(Collectors.toMap(FlightRoute::getCode, flightRoute -> flightRoute));
        return cvsDataBeanMap;
    }


    @Override
    FlightRoute createDataBeanByList(List<String> listBean){
        return FactoryReadData.createFlightRouteByList(listBean);
    }



}
