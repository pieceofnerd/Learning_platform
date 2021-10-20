package com.sytoss.mapper.education;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.education.Purchase;
import com.sytoss.web.dto.PurchaseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseMapper extends BaseMapper<Purchase, PurchaseDTO> {
    PurchaseMapper() {
        super(Purchase.class, PurchaseDTO.class);
    }

    @Override
    public Purchase toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public PurchaseDTO toDTO(Purchase entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Purchase> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<PurchaseDTO> toListDTO(List<Purchase> purchases) {
        return super.toListDTO(purchases);
    }
}
