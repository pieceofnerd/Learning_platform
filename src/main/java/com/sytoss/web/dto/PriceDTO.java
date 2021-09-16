package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO   {

    private Long id;

    private Long courseId;

    private Long priceTypeId;

    private BigDecimal cost;

    private Long promotionId;

}
