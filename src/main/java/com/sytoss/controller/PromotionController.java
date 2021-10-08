package com.sytoss.controller;

import com.sytoss.exception.no_contet_exception.PromotionNoContentException;
import com.sytoss.mapper.PromotionMapper;
import com.sytoss.model.course.Promotion;
import com.sytoss.service.PromotionService;
import com.sytoss.web.dto.PromotionDTO;
import com.sytoss.web.dto.save.PromotionSaveDTO;
import org.springframework.stereotype.Component;

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
        } catch (PromotionNoContentException e) {
            e.printStackTrace();
        }
        return promotionMapper.toDTO(promotion);
    }

}
