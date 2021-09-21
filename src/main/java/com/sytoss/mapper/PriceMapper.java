package com.sytoss.mapper;

import com.sytoss.model.course.Price;
import com.sytoss.web.dto.PriceDTO;
import com.sytoss.web.dto.save.PriceSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceMapper extends BaseMapper<Price, PriceDTO> {

    protected PriceMapper() {
        super(Price.class, PriceDTO.class);
    }

    @Override
    public Price toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public PriceDTO toDTO(Price entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Price> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<PriceDTO> toListDTO(List<Price> prices) {
        return super.toListDTO(prices);
    }
}
