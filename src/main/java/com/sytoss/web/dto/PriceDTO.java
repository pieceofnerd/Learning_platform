package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PriceDTO   {

    private Long id;

    private Long courseId;

    private Long priceTypeId;

    private BigDecimal cost;

    private Long promotionId;

}
