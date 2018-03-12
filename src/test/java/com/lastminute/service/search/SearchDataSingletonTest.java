package com.lastminute.service.search;

import com.lastminute.model.FlightOffer;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Test
public class SearchDataSingletonTest {

    private SearchDataSingleton searchDataSingleton;

    @Test
    public void checkOnlyOneInstanceCreateTest() {
        List<FlightOffer> flightOfferList = new ArrayList<FlightOffer>();
        SearchDataSingleton searchDataSingleton1 = SearchDataSingleton.getInstance();
        searchDataSingleton1.getSearchData().put("AAA",flightOfferList);
        searchDataSingleton1.getOriginData().add("ABC");
        searchDataSingleton1.getDestinationData().add("CBA");
        searchDataSingleton1.setCreateDataFlag(true);
        SearchDataSingleton searchDataSingleton2 = SearchDataSingleton.getInstance();
        assertTrue(searchDataSingleton2.getOriginData().contains("ABC"));
        assertTrue(searchDataSingleton2.getDestinationData().contains("CBA"));
        assertEquals(searchDataSingleton2.getSearchData().get("AAA"),flightOfferList);
        assertTrue(searchDataSingleton2.isCreateDataFlag());
        assertEquals(searchDataSingleton1,searchDataSingleton2);

    }
}
