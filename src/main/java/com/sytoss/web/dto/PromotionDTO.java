package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {

    private Long id;

    private String name;

    private Integer percent;

    private Date startDate;

    private Date endDate;

    private LookupDTO promotionState;

    private List<PriceDTO> prices;
}
