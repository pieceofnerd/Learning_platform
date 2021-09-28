package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class FilterCommunicationDTO {

    private Filter filter;

    private Long lessonId;

    private Date startTimePeriod;

    private Date endTimePeriod;

}
