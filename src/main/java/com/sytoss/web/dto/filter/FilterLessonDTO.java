package com.sytoss.web.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class FilterLessonDTO {

    private Filter filter;

    private Long studyGroupId;

    private Long lessonTemplateId;

    private Long mentorId;

    private Date startTimePeriod;

    private Date endDatePeriod;

}
