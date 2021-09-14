package com.sytoss.mapper;

import com.sytoss.model.course.Price;
import com.sytoss.web.dto.PriceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper extends BaseMapper<Price, PriceDTO> {

    protected PriceMapper(ModelMapper mapper) {
        super(Price.class, PriceDTO.class);
    }

    @Override
    public Price toEntity(PriceDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public PriceDTO toDTO(Price entity) {
        return super.toDTO(entity);
    }
}