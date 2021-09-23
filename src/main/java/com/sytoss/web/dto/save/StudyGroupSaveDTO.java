package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyGroupSaveDTO {

    private Long course;

    private Integer placeNumber;

    private Integer freePlaceNumber;

    private Date startDate;

    private Date endDate;

}