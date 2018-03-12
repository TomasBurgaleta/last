package com.lastminute.service.purchase;

import com.lastminute.model.FlightBooking;
import com.lastminute.model.PurchaseResult;
import com.lastminute.model.SearchDataBean;
import java.util.List;

public interface Purchase {

    public PurchaseResult getPurchase(SearchDataBean searchDataBean);
}
