package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.PromotionNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPriceException;
import com.sytoss.exception.no_such_exception.NoSuchPromotionException;
import com.sytoss.model.course.Promotion;
import com.sytoss.web.dto.filter.FilterPromotionDTO;

import java.util.List;

public interface PromotionService {

    Promotion createPromotion(Promotion promotion) throws PromotionNoContentException, NoSuchPriceException;

    Promotion updatePromotion(Promotion promotion) throws NoSuchPromotionException;

    List<Promotion> findPromotionsByFilter(FilterPromotionDTO filter);

}
