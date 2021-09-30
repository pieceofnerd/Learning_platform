package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {
    private Long id;

    private String name;

    private String description;

    private CourseDTO course;

    private List<LessonTemplateDTO> lessonTemplates;

    private Boolean active;
}
