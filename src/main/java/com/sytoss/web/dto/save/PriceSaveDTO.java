package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceSaveDTO {

    private Long priceTypeId;

    private BigDecimal cost;

    private Long courseId;

    private PromotionSaveDTO promotion;
}
