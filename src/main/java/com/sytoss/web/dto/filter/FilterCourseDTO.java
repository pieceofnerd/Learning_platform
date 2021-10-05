package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class FilterCourseDTO {

    private Filter filter;

    private BigDecimal lowCost;

    private BigDecimal highCost;

    private Long studentId;

    private Long categoryId;

}
