package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PriceSaveDTO {

    private Long priceTypeId;

    private BigDecimal cost;

    @Setter
    private Long courseId;

    private PromotionSaveDTO promotion;
}
