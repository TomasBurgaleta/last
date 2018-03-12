package com.lastminute.service.offers;

import com.lastminute.model.FlightRoute;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.service.search.SearchDataImpl;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import static org.testng.Assert.assertEquals;

public class SearchDataBeanTest {


    private final int flightCount = 89;

    @Test
    public void readFlightRoutes() throws ReadCSVException {
        SearchDataImpl generateSearchData = new SearchDataImpl();
        //Map<String, List<FlightRoute>> data = generateSearchData.getAllSearchData();

        //allFlightsRead(data);
    }

    private void allFlightsRead(Map<String, List<FlightRoute>> flights)
    {
        assertEquals(flightCount, flights.size());
    }
}
