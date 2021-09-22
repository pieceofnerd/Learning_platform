package com.sytoss.service;

import com.sytoss.exception.NoSuchPriceException;
import com.sytoss.model.course.Price;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.save.PriceSaveDTO;

public interface PriceService {

    void createPrice(Price price);

    void updatePrice(Price price) throws NoSuchPriceException;

}
