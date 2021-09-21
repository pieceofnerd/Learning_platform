package com.sytoss.service;

import com.sytoss.exception.NoSuchPromotionException;
import com.sytoss.model.course.Promotion;
import com.sytoss.web.dto.filter.FilterPromotionDTO;

import java.util.List;

public interface PromotionService {

    void createPromotion(Promotion promotion);

    void updatePromotion(Promotion promotion) throws NoSuchPromotionException;

    List<Promotion> findPromotionsByFilter(FilterPromotionDTO filter);

}
