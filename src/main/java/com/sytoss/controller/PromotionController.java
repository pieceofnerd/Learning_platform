package com.sytoss.controller;

import com.sytoss.exception.no_contet_exception.PromotionNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPriceException;
import com.sytoss.exception.no_such_exception.NoSuchPromotionException;
import com.sytoss.mapper.course.PromotionMapper;
import com.sytoss.model.course.Promotion;
import com.sytoss.service.PromotionService;
import com.sytoss.web.dto.PromotionDTO;
import com.sytoss.web.dto.filter.FilterPromotionDTO;
import com.sytoss.web.dto.save.PromotionSaveDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromotionController {

    private  final PromotionService promotionService;

    private final PromotionMapper promotionMapper;

    public PromotionController(PromotionService promotionService, PromotionMapper promotionMapper) {
        this.promotionService = promotionService;
        this.promotionMapper = promotionMapper;
    }


    public PromotionDTO createPromotion(PromotionSaveDTO promotionSaveDTO){
        Promotion promotion = promotionMapper.toEntity(promotionSaveDTO);
        try {
           promotion= promotionService.createPromotion(promotion);
        } catch (PromotionNoContentException | NoSuchPriceException e) {
            e.printStackTrace();
        }
        return promotionMapper.toDTO(promotion);
    }

    public List<PromotionDTO> findPromotionsByFilter(FilterPromotionDTO filterPromotionDTO){
        List<Promotion> promotions = promotionService.findPromotionsByFilter(filterPromotionDTO);
        return promotionMapper.toListDTO(promotions);
    }

    public PromotionDTO updatePromotion(PromotionDTO promotionDTO){
        Promotion promotion=promotionMapper.toEntity(promotionDTO);
        try {
           promotion= promotionService.updatePromotion(promotion);
        } catch (NoSuchPromotionException e) {
            e.printStackTrace();
        }
        return promotionMapper.toDTO(promotion);
    }

}
