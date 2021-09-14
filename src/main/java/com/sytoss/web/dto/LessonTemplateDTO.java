package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LessonTemplateDTO {

    private String name;

    private  String description;

    private MediaDTO media;

    private Integer duration;

}
