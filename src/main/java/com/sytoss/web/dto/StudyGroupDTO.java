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
public class StudyGroupDTO {

    private Long id;

    private CourseDTO course;

    private Integer placeNumber;

    private Boolean deleted;

    private Date startDate;

    private Date endDate;

    private Date createdDate;

    private Date updatedDate;

    private List<StudyDTO> studies;

    private List<LessonDTO> lessons;


}