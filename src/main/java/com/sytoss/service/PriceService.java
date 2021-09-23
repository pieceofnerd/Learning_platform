package com.sytoss.service;

import com.sytoss.exception.NoSuchPriceException;
import com.sytoss.model.course.Price;

public interface PriceService {

    void createPrice(Price price);

    void updatePrice(Price price) throws NoSuchPriceException;

}
