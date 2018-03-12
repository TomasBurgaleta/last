package com.lastminute.service.purchase;

import com.lastminute.model.*;
import com.lastminute.service.read.FactoryReadData;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.service.search.SearchData;
import com.lastminute.utils.StandardUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PurchaseImplTest {

    @Mock
    private SearchData searchData;


    private Purchase purchase = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeMethod
    public void setUp() throws ReadCSVException {
        MockitoAnnotations.initMocks(this);
        purchase = new PurchaseImpl(searchData);
        String keySearch = StandardUtils.generateOriginDestinationKey("MAD", "BCN");
        when(searchData.getOfferRoute(keySearch)).thenReturn(createFlightRouteData());
        when(searchData.getDestination()).thenReturn(Arrays.asList("BCN"));
        when(searchData.getOrigins()).thenReturn(Arrays.asList("MAD"));
    }


    private List<FlightOffer> createFlightRouteData() {

        FlightRoute flightRoute1 = FactoryReadData.createFlightRouteByList(Arrays.asList("MAD","BCN","IB2818"));
        FlightPrice flightPrice1 = FactoryReadData.createFlightCodeByList(Arrays.asList("IB2818","186"));
        FlightOffer flightOffer1 = new FlightOffer(flightRoute1, flightPrice1);
;

        FlightRoute flightRoute2 = FactoryReadData.createFlightRouteByList(Arrays.asList("MAD","BCN","UX1200"));
        FlightPrice flightPrice2 = FactoryReadData.createFlightCodeByList(Arrays.asList("UX1200","100"));
        FlightOffer flightOffer2 = new FlightOffer(flightRoute2, flightPrice2);


        List<FlightOffer> flightRouteList = new ArrayList<FlightOffer>(Arrays.asList(flightOffer1, flightOffer2));

        return flightRouteList ;
    }
    @Test
    public void offerTest() throws ParseException {
        SearchDataBean searchDataBean = new SearchDataBean();
        searchDataBean.setOrigin("MAD");
        searchDataBean.setDestination("BCN");
        searchDataBean.setNumberPassengers(1);
        searchDataBean.setDepartureDate(dateFormat.parse("20/04/2018"));
        PurchaseResult purchaseResult = purchase.getPurchase(searchDataBean);
        assertEquals(2, purchaseResult.getFlightBookingList().size());
        assertTrue(purchaseResult.isErrorEmpty());

    }


}
