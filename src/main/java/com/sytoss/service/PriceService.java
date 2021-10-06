package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.PriceNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPriceException;
import com.sytoss.model.course.Price;

public interface PriceService {

    void createPrice(Price price) throws PriceNoContentException;

    void updatePrice(Price price) throws NoSuchPriceException;

}
