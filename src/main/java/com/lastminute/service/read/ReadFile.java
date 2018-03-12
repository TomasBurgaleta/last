package com.lastminute.service.read;

import java.util.Map;

public interface ReadFile<T> {


    Map<String, T> getDataBeanMap() throws ReadCSVException;

}
