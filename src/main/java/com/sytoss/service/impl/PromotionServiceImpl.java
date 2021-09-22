package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchPromotionException;
import com.sytoss.model.course.Promotion;
import com.sytoss.repository.course.PromotionRepository;
import com.sytoss.service.PromotionService;
import com.sytoss.web.dto.filter.FilterPromotionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PromotionServiceImpl implements PromotionService {

    private final static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void createPromotion(Promotion promotion) {
        if (promotion == null)
            throw new NullPointerException();

        promotionRepository.save(promotion);
    }

    @Override
    public void updatePromotion(Promotion promotion) throws NoSuchPromotionException {
        if (promotionRepository.findOne(promotion.getId()) == null)
            throw new NoSuchPromotionException();
        promotionRepository.save(promotion);
    }

    @Override
    public List<Promotion> findPromotionsByFilter(FilterPromotionDTO filter) {
        switch (filter.getFilter()) {
            case TIME_PERIOD:
                return promotionRepository.findPromotionsByTimePeriod(filter.getStartTimePeriod(), filter.getEndTimePeriod());
        }
        return null;
    }

}
