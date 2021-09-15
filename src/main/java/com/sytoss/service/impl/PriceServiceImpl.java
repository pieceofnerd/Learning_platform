package com.sytoss.service.impl;

import com.sytoss.mapper.PriceMapper;
import com.sytoss.model.course.Price;
import com.sytoss.repository.PriceRepository;
import com.sytoss.service.PriceService;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.save.PriceSaveDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public boolean createPrice(PriceSaveDTO priceSaveDTO) {
        Price price = priceMapper.toEntity(priceSaveDTO);
        return savePrice(price);
    }


    @Override
    public boolean updatePrice(PriceDTO priceDTO) {
        Price price = priceMapper.toEntity(priceDTO);
        return savePrice(price);
    }

    private boolean savePrice(Price price) {
        try {
            validatePrice(price.getCost());
            priceRepository.save(price);
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("Price {} less than minimum price", price.getPriceType());
            return false;
        }
    }

    private void validatePrice(BigDecimal cost) throws IllegalArgumentException {
        if (cost.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("Price " + cost + "less than minimum price");
        }
    }

}
