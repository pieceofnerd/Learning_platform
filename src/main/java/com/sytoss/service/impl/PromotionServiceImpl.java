package com.sytoss.service.impl;

import com.sytoss.exception.no_contet_exception.PromotionNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPriceException;
import com.sytoss.exception.no_such_exception.NoSuchPromotionException;
import com.sytoss.model.Lookup;
import com.sytoss.model.course.Price;
import com.sytoss.model.course.Promotion;
import com.sytoss.model.enums.PromotionStatus;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.PromotionRepository;
import com.sytoss.service.PriceService;
import com.sytoss.service.PromotionService;
import com.sytoss.web.dto.filter.FilterPromotionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PriceService priceService;

    private final PromotionRepository promotionRepository;

    private final LookupRepository lookupRepository;

    @Autowired
    public PromotionServiceImpl(PriceService priceService, PromotionRepository promotionRepository,
                                LookupRepository lookupRepository) {
        this.priceService = priceService;
        this.promotionRepository = promotionRepository;
        this.lookupRepository = lookupRepository;
    }

    @Override
    public Promotion createPromotion(Promotion promotion) throws PromotionNoContentException, NoSuchPriceException {
        if (promotion == null) {
            logger.error("Cannot save promotion with null value");
            throw new PromotionNoContentException("Promotion is null");
        }
        promotion.setPromotionState(lookupRepository.findOne(PromotionStatus.ANNOUNCED.getValue()));
        Promotion savedPromotion = promotionRepository.save(promotion);
        for (Price price : promotion.getPrices()) {
            price.setPromotion(promotion);
            priceService.updatePrice(price);
        }
        logger.info("Promotion {} was created", savedPromotion.getId());
        return savedPromotion;
    }

    @Override
    public void updatePromotion(Promotion promotion) throws NoSuchPromotionException {
        if (!promotionRepository.exists(promotion.getId())) {
            logger.error("Couldn't find promotion with id: {}", promotion.getId());
            throw new NoSuchPromotionException("No such promotion exists");
        }
        promotionRepository.save(promotion);
        logger.info("Promotion {} was updated", promotion.getId());
    }

    @Override
    public List<Promotion> findPromotionsByFilter(FilterPromotionDTO filter) {
        List<Promotion> promotions = new ArrayList<>();
        switch (filter.getFilter()) {
            case TIME_PERIOD:
                promotions.addAll(promotionRepository.findPromotionsByTimePeriod(filter.getStartTimePeriod(), filter.getEndTimePeriod()));
                break;
            case PROMOTION_STATE:
                Lookup lookup = lookupRepository.findOne(filter.getPromotionStateId());
                promotions.addAll(promotionRepository.findPromotionsByPromotionState(lookup));
            break;
        }
        return promotions;
    }

}
