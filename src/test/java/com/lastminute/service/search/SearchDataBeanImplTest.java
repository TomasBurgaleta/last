package com.lastminute.service.search;

import com.lastminute.model.FlightBooking;
import com.lastminute.model.FlightOffer;
import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightRoute;
import com.lastminute.service.read.FactoryReadData;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.utils.StandardUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@Test
public class SearchDataBeanImplTest {


    @Mock
    private SearchDataSingleton searchDataSingleton;

    @InjectMocks
    private SearchDataImpl searchData;

    @BeforeMethod
    public void setUp() throws ReadCSVException {

        MockitoAnnotations.initMocks(this);
        Set<String> originData = new HashSet<String>(Arrays.asList("LHR", "FRA"));
        Set<String> destinationData = new HashSet<String>(Arrays.asList("AMS", "IST", "LHR"));
        Map<String, List<FlightOffer>> flightRouteListMap = new HashMap<String, List<FlightOffer>>();
        FlightRoute flightRoute = FactoryReadData.createFlightRouteByList(Arrays.asList("CPH","FRA","IB2818"));
        FlightPrice flightPrice = FactoryReadData.createFlightCodeByList(Arrays.asList("IB2818","100"));
        FlightOffer flightOffer = new FlightOffer(flightRoute, flightPrice);
        flightRouteListMap.put("CPH_FRA",Arrays.asList(flightOffer));

        when(searchDataSingleton.getOriginData()).thenReturn(originData);
        when(searchDataSingleton.getDestinationData()).thenReturn(destinationData);
        when(searchDataSingleton.getSearchData()).thenReturn(flightRouteListMap);
    }


    @Test
    public void destinationDataTest(){
       List<String> destinationData = searchData.getDestination();
       assertFalse(destinationData.isEmpty());
       assertEquals(3, destinationData.size());
       verify(searchDataSingleton, times(1)).getDestinationData();
    }

    @Test
    public void originDataTest(){
        List<String> destinationData = searchData.getOrigins();
        assertFalse(destinationData.isEmpty());
        assertEquals(2, destinationData.size());
        verify(searchDataSingleton, times(1)).getOriginData();

    }

    public void  getOfferRouteTest(){
        String key1 = StandardUtils.generateOriginDestinationKey("CPH","FRA");
        String key2 = StandardUtils.generateOriginDestinationKey("AAA","FRA");
        List<FlightOffer> flightOfferList1 = searchData.getOfferRoute(key1);
        List<FlightOffer> flightOfferList2 = searchData.getOfferRoute(key2);
        assertFalse(flightOfferList1.isEmpty());
        assertTrue(flightOfferList2.isEmpty());
        assertEquals(1, flightOfferList1.size());
        assertEquals(0, flightOfferList2.size());
        verify(searchDataSingleton, times(3)).getSearchData();
    }




}
