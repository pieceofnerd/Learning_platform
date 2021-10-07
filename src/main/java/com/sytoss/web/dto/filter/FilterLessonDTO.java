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
public class FilterLessonDTO {

    private Filter filter;

    private Long studyGroupId;

    private Long lessonTemplateId;

    private Long mentorId;

    private Date startTimePeriod;

    private Date endDatePeriod;

}
