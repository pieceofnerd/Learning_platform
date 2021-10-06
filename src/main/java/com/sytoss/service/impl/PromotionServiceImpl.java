package com.sytoss.service.impl;

import com.sytoss.exception.no_such_exception.NoSuchPromotionException;
import com.sytoss.model.Lookup;
import com.sytoss.model.course.Promotion;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.PromotionRepository;
import com.sytoss.service.PromotionService;
import com.sytoss.web.dto.filter.FilterPromotionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    private final LookupRepository lookupRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository, LookupRepository lookupRepository) {
        this.promotionRepository = promotionRepository;
        this.lookupRepository = lookupRepository;
    }

    @Override
    public void createPromotion(Promotion promotion) {
        if (promotion == null) {
            logger.error("Cannot save promotion with null value");
            throw new NullPointerException();
        }
        Promotion savedPromotion = promotionRepository.save(promotion);
        logger.info("Promotion {} was created", savedPromotion.getId());
    }

    @Override
    public void updatePromotion(Promotion promotion) throws NoSuchPromotionException {
        if (!promotionRepository.exists(promotion.getId())) {
            logger.error("Couldn't find promotion with id: {}", promotion.getId());
            throw new NoSuchPromotionException();
        }
        promotionRepository.save(promotion);
        logger.info("Promotion {} was updated", promotion.getId());
    }

    @Override
    public List<Promotion> findPromotionsByFilter(FilterPromotionDTO filter) {
        switch (filter.getFilter()) {
            case TIME_PERIOD:
                return promotionRepository.findPromotionsByTimePeriod(filter.getStartTimePeriod(), filter.getEndTimePeriod());
            case PROMOTION_STATE: {
                Lookup lookup = lookupRepository.findOne(filter.getPromotionStateId());
                return promotionRepository.findPromotionsByPromotionState(lookup);
            }
        }
        return null;
    }

}
