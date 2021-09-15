package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PriceSaveDTO {

    private String priceType;

    private BigDecimal cost;

    @Setter
    private CourseDTO courseDTO;

    private PromotionSaveDTO promotion;
}
