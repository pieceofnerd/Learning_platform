package com.sytoss.service;

import com.sytoss.model.course.Price;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.save.PriceSaveDTO;

public interface PriceService {

    boolean createPrice(PriceSaveDTO priceSaveDTO);

    boolean updatePrice(PriceDTO priceDTO);

}
