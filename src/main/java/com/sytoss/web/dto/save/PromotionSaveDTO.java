package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.PriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class PromotionSaveDTO {

    private String name;

    private int percent;

    private Date startDate;

    private Date endDate;

    private List<PriceDTO> prices;

}