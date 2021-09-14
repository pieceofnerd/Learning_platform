package com.sytoss.web.dto;


import com.sytoss.model.course.Course;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.education.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Getter
public class StudyGroupDTO {

    private CourseDTO course;

    private Integer placeNumber;

    private Date startDate;

    private Date endDate;

    private Date createdDate;

    private Date updatedDate;

    private List<StudyDTO> studies;

    private List<Lesson> lessons;
}