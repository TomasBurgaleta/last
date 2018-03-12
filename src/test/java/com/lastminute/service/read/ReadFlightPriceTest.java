package com.lastminute.service.read;

import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


public class ReadFlightPriceTest {


    private final int flightCount = 3;

    @InjectMocks
    private ReadFlightPrice readFlightPrice;
    @Mock
    private CsvFiles csvFiles;
    @Mock
    private UncheckedIOException uncheckedIOException;

    @BeforeMethod
    public void setUp() throws ReadCSVException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void readFlightPriceTest() throws ReadCSVException {
        List<List<String>> csvList = createListData();
        when(csvFiles.readAllRecords("flight-prices.csv")).thenReturn(csvList);
        Map<String, FlightPrice> dataBeanMap = readFlightPrice.getDataBeanMap();
        allFlightsRead(dataBeanMap);
    }
    private List<List<String>> createListData() {
        List<String> list1 = Arrays.asList("IB2818","186");
        List<String> list2 = Arrays.asList("U23631","152");
        List<String> list3 = Arrays.asList("IB8482","295");
        List<String> list4 = Arrays.asList("IB8481","FFF");
        List<List<String>> csvList = Arrays.asList(list1, list2, list3, list4);
        return csvList;
    }

    @Test(expectedExceptions = ReadCSVException.class)
    public void readFlightRoutesUncheckedIOException() throws ReadCSVException {
        when(csvFiles.readAllRecords("flight-prices.csv")).thenThrow(uncheckedIOException);
        Map<String, FlightPrice> dataBeanMap = readFlightPrice.getDataBeanMap();

    }


    private void allFlightsRead( Map<String, FlightPrice> flightCodeMap)
    {
        assertEquals(flightCount, flightCodeMap.size());
    }
}
