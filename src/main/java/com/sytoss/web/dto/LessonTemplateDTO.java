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
public class LessonTemplateDTO {

    private Long id;

    private String name;

    private String description;

    private MediaDTO media;

    private Integer duration;

    private TopicDTO topic;

    private Boolean active;

    private List<LessonDTO> lessons;
}
