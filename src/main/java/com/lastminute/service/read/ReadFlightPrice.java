package com.lastminute.service.read;

import com.lastminute.model.FlightPrice;
import com.lastminute.utils.StandardUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadFlightPrice extends ReadFileAbstract<FlightPrice> {

    private static final String CVS_FILE ="flight-prices.csv";

    public ReadFlightPrice() {
        super(CVS_FILE);
    }

//    @Override
//    public Map<String, FlightPrice> getDataBeanMap() throws ReadCSVException {
//        List<FlightPrice> listData = getAllData();
//        Map<String, FlightPrice> cvsDataBeanMap = new HashMap<String, FlightPrice>();
//        for (FlightPrice flightPrice : listData){
//            String code = flightPrice.getCode();
//            if (StandardUtils.notNullOrEmptyString(code))
//                cvsDataBeanMap.put(code, flightPrice);
//        }
//        return cvsDataBeanMap;
//    }
    //TODO: use lambda
    @Override
    public Map<String, FlightPrice> getDataBeanMap() throws ReadCSVException {
       List<FlightPrice> listData = getAllData();
       Map<String, FlightPrice> cvsDataBeanMap =
               listData.stream().filter(u -> u.getCode() != null).collect(Collectors.toMap(FlightPrice::getCode, flightPrice -> flightPrice));
        return cvsDataBeanMap;
    }

    @Override
    FlightPrice createDataBeanByList(List<String> listBean) {
        return FactoryReadData.createFlightCodeByList(listBean);
    }

}
