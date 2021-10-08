package com.sytoss.mapper;

import com.sytoss.model.course.Promotion;
import com.sytoss.web.dto.PromotionDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromotionMapper extends BaseMapper<Promotion, PromotionDTO> {

    protected PromotionMapper() {
        super(Promotion.class, PromotionDTO.class);
    }

    @Override
    public Promotion toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public PromotionDTO toDTO(Promotion entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Promotion> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<PromotionDTO> toListDTO(List<Promotion> prices) {
        return super.toListDTO(prices);
    }

}
