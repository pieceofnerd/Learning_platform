package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PriceDTO {

    private String priceType;

    private BigDecimal cost;

    private PromotionDTO promotion;

}
