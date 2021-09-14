package com.sytoss.web.dto.save;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TopicSaveDTO {

    private String name;

    private  String description;

    private List<LessonTemplateSaveDTO> lessonTemplates;

}
