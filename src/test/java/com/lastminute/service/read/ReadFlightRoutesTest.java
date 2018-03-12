package com.lastminute.service.read;

import com.lastminute.model.FlightRoute;
import com.lastminute.service.search.DataSearchGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class ReadFlightRoutesTest {

    private final int flightCount = 3;

    @InjectMocks
    private ReadFlightRoutes readFlightRoutes;
    @Mock
    private CsvFiles csvFiles;
    @Mock
    private UncheckedIOException uncheckedIOException;

    @BeforeMethod
    public void setUp() throws ReadCSVException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void readFlightRoutesTest() throws ReadCSVException {
        List<List<String>> csvList = createListData();
        when(csvFiles.readAllRecords("flight-routes.csv")).thenReturn(csvList);
        Map<String, FlightRoute> dataBeanMap = readFlightRoutes.getDataBeanMap();
        allFlightsRead(dataBeanMap);
    }

    private List<List<String>> createListData() {
        List<String> list1 = Arrays.asList("CPH", "FRA", "IB2818");
        List<String> list2 = Arrays.asList("CPH", "LHR", "U23631");
        List<String> list3 = Arrays.asList("CDG", "MAD", "IB8482");
        List<String> list4 = Arrays.asList("CDG");
        List<List<String>> csvList = Arrays.asList(list1,list2,list3, list4);
        return csvList;
    }


    @Test(expectedExceptions = ReadCSVException.class)
    public void readFlightRoutesUncheckedIOException() throws ReadCSVException {
        when(csvFiles.readAllRecords("flight-routes.csv")).thenThrow(uncheckedIOException);
        Map<String, FlightRoute> dataBeanMap = readFlightRoutes.getDataBeanMap();

    }


    private void allFlightsRead(Map<String, FlightRoute> dataBeanMap)
    {
        assertEquals(flightCount, dataBeanMap.size());
    }


}
