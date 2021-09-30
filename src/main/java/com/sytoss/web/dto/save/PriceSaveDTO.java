package com.sytoss.web.dto.save;

import com.sytoss.model.Lookup;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.LookupDTO;
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

    private LookupDTO priceType;

    private BigDecimal cost;

    private PromotionSaveDTO promotion;
}
