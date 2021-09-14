package com.sytoss.mapper;

import com.sytoss.model.course.Price;
import com.sytoss.web.dto.save.PriceSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper extends BaseMapper<Price, PriceSaveDTO> {

    protected PriceMapper(ModelMapper mapper) {
        super(Price.class, PriceSaveDTO.class);
    }

    @Override
    public Price toEntity(PriceSaveDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public PriceSaveDTO toDTO(Price entity) {
        return super.toDTO(entity);
    }
}
