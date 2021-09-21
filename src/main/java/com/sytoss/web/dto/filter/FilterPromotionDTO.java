package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class FilterPromotionDTO {

   private Filter filter;

   private Date startTimePeriod;

   private Date endTimePeriod;

   private Long promotionStateId;

}
