package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@AllArgsConstructor
public class TopicDTO {

    private String name;

    private  String description;

    private List<LessonTemplateDTO> lessonTemplates;

}
