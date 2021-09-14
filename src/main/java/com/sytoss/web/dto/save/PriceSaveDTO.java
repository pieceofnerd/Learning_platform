package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PriceSaveDTO {

    private String priceType;

    private BigDecimal cost;

    private PromotionSaveDTO promotion;

}
