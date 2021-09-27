package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
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

    private CourseDTO course;

    private PromotionSaveDTO promotion;
}
