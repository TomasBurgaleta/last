package com.lastminute.service.console;

import com.lastminute.model.*;
import com.lastminute.service.purchase.Purchase;
import com.lastminute.service.purchase.PurchaseImpl;
import com.lastminute.service.read.FactoryReadData;
import com.lastminute.service.read.ReadCSVException;
import com.lastminute.service.search.SearchData;
import com.lastminute.utils.StandardUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class ConsoleManagerImplTest {




    private ConsoleManagerImpl consoleManagerImpl = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Mock
    private SearchData searchData;
    @Mock
    private Purchase purchase;

    @BeforeMethod
    public void setUp() throws ReadCSVException {
        MockitoAnnotations.initMocks(this);
        consoleManagerImpl = new ConsoleManagerImpl(searchData, purchase);

    }

    @Test
    public void executeSearchCommandTest() {
        String command= "search MAD BCN 25/04/2018 1";
        PurchaseResult purchaseResult = createPurchaseResult();
        when(purchase.getPurchase(any(SearchDataBean.class))).thenReturn(purchaseResult);
        consoleManagerImpl.executeCommand(command);
        verify(purchase, times(1)).getPurchase(any(SearchDataBean.class));

    }

    private PurchaseResult createPurchaseResult(){
        PurchaseResult purchaseResult = new PurchaseResult();
        FlightBooking flightBooking = new FlightBooking();
        FlightRoute flightRoute = FactoryReadData.createFlightRouteByList(Arrays.asList("MAD","BCN","UX1200"));
        FlightPrice flightPrice = FactoryReadData.createFlightCodeByList(Arrays.asList("UX1200","100"));
        FlightOffer flightOffer = new FlightOffer(flightRoute, flightPrice);
        flightBooking.setFlightOffer(flightOffer);
        flightBooking.setNumPassengers(2);
        flightBooking.setPriceModifierType(PriceModifierType.STANDARD_PRIORITY);
        flightBooking.setFinalPriceByPassenger(new BigDecimal(150));
        purchaseResult.addFlightBookingList(flightBooking);
        return purchaseResult;
    }

    @Test
    public void executeOriginCommandTest() {
        String command= "origin";
        consoleManagerImpl.executeCommand(command);
        when(searchData.getOrigins()).thenReturn(Arrays.asList("MAD"));
        verify(searchData, times(1)).getOrigins();
        verify(searchData, times(0)).getDestination();
    }

    @Test
    public void executeDestinationCommandTest() {
        String command= "destination";
        consoleManagerImpl.executeCommand(command);
        when(searchData.getDestination()).thenReturn(Arrays.asList("BCN"));
        verify(searchData, times(0)).getOrigins();
        verify(searchData, times(1)).getDestination();
    }

}
