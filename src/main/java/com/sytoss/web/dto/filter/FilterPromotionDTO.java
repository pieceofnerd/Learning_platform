package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilterPromotionDTO {

   private Filter filter;

   private Date startTimePeriod;

   private Date endTimePeriod;

   private Long promotionStateId;

}
