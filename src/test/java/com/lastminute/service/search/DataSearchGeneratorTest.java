package com.lastminute.service.search;

import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import com.lastminute.service.read.FactoryReadData;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.service.read.ReadFile;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataSearchGeneratorTest {

    @Mock
    private SearchDataSingleton searchDataSingleton;
    @Mock
    private ReadFile<FlightPrice> readFlightPrice;
    @Mock
    private ReadFile<FlightRoute> readFlightRoutes;
    @Mock
    private ReadCSVException readCSVException;

    private DataSearchGenerator dataSearchGenerator;

    @BeforeMethod
    public void setUp() throws ReadCSVException {
        MockitoAnnotations.initMocks(this);
        dataSearchGenerator = new DataSearchGenerator(searchDataSingleton, readFlightPrice, readFlightRoutes);


    }

    private Map<String, FlightPrice> createFlightCodeData() {
        Map<String, FlightPrice> flightCodeMap = new HashMap<String, FlightPrice>();
        FlightPrice flightPrice1 = FactoryReadData.createFlightCodeByList(Arrays.asList("IB2818","186"));
        FlightPrice flightPrice2 = FactoryReadData.createFlightCodeByList(Arrays.asList("U23631","152"));
        FlightPrice flightPrice3 = FactoryReadData.createFlightCodeByList(Arrays.asList("IB8482","295"));
        flightCodeMap.put("IB2818", flightPrice1);
        flightCodeMap.put("U23631", flightPrice2);
        flightCodeMap.put("IB8482", flightPrice3);
        return flightCodeMap;
    }

    private  Map<String, FlightRoute> createFlightRouteData() {
        Map<String, FlightRoute> flightRouteMap = new HashMap<String, FlightRoute>();
        FlightRoute flightRoute1 = FactoryReadData.createFlightRouteByList(Arrays.asList("CPH","FRA","IB2818"));
        FlightRoute flightRoute2 = FactoryReadData.createFlightRouteByList(Arrays.asList("CPH","LHR","U23631"));
        FlightRoute flightRoute3 = FactoryReadData.createFlightRouteByList(Arrays.asList("CDG","MAD","IB8482"));
        FlightRoute flightRoute4 = FactoryReadData.createFlightRouteByList(Arrays.asList("BCN","FRA","FR7521"));
        flightRouteMap.put("IB2818",flightRoute1);
        flightRouteMap.put("U23631",flightRoute2);
        flightRouteMap.put("IB8482",flightRoute3);
        flightRouteMap.put("FR7521",flightRoute4);
        return flightRouteMap ;
    }

    @Test
    public void generateDataTest() throws ReadCSVException {
        Map<String, FlightPrice> flightCodeMap = createFlightCodeData();
        Map<String, FlightRoute> flightRouteMap = createFlightRouteData();
        when(readFlightPrice.getDataBeanMap()).thenReturn(flightCodeMap);
        when(readFlightRoutes.getDataBeanMap()).thenReturn(flightRouteMap);
        dataSearchGenerator.readFiles();
        verify(readFlightPrice,times(1)).getDataBeanMap();
        verify(readFlightRoutes,times(1)).getDataBeanMap();
        verify(searchDataSingleton).setCreateDataFlag(true);
        verify(searchDataSingleton,times(1)).getOriginData();
        verify(searchDataSingleton,times(1)).getDestinationData();
        verify(searchDataSingleton,times(3)).getSearchData();
    }
    @Test
    public void generateDataErrorTest() throws ReadCSVException {
        when(readFlightPrice.getDataBeanMap()).thenThrow(readCSVException);
        dataSearchGenerator.readFiles();
        verify(readFlightPrice,times(1)).getDataBeanMap();
        verify(searchDataSingleton).setCreateDataFlag(false);
        //verify(readFlightPrice,times(0)).getDataBeanMap();
    }


}
