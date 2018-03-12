package com.lastminute.service.search;


import com.lastminute.model.FlightOffer;

import java.util.*;

public class SearchDataSingleton {


    private static SearchDataSingleton INSTANCE = null;
    private static Map<String, List<FlightOffer>> SEARCH_DATA = null;
    private static  Set<String> ORIGIN_SET = null;
    private static  Set<String> DESTINATION_SET = null;
    private static boolean CREATE_DATA_FLAG = false;


    private SearchDataSingleton(){
        SEARCH_DATA = new HashMap<String, List<FlightOffer>>();
        ORIGIN_SET = new HashSet<String>();
        DESTINATION_SET = new HashSet<String>();
    }

    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SearchDataSingleton();
        }
    }

    public static SearchDataSingleton getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    protected Map<String, List<FlightOffer>> getSearchData() {
        return SEARCH_DATA;
    }

    protected Set<String> getOriginData() {
        return ORIGIN_SET;
    }

    protected Set<String> getDestinationData() {
        return DESTINATION_SET;
    }

    protected boolean isCreateDataFlag() {
        return CREATE_DATA_FLAG;
    }

    protected void setCreateDataFlag(boolean createDataFlag) {
        CREATE_DATA_FLAG = createDataFlag;
    }
}
