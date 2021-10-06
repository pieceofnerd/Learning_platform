package com.sytoss.service.impl;

import com.sytoss.exception.no_such_exception.NoSuchPriceException;
import com.sytoss.model.course.Price;
import com.sytoss.repository.course.PriceRepository;
import com.sytoss.service.PriceService;
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

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void createPrice(Price price) throws IllegalArgumentException {
        savePrice(price);
        logger.info("Price {} was created ", price.toString());
    }


    @Override
    public void updatePrice(Price price) throws NoSuchPriceException, IllegalArgumentException {
        if (!priceRepository.exists(price.getId())) {
            logger.error("Couldn't find price with id: {}", price.getId());
            throw new NoSuchPriceException();
        }
        validatePrice(price.getCost());
        priceRepository.save(price);
    }

    private void savePrice(Price price) throws IllegalArgumentException {
        if (price == null) {
            logger.error("Price must be not null");
            return;
        }
        validatePrice(price.getCost());
        priceRepository.save(price);
    }

    private void validatePrice(BigDecimal cost) throws IllegalArgumentException {
        if (cost.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("Price " + cost + "less than minimum price");
        }
    }

}
