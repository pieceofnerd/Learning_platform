package com.sytoss.web.dto.save;

import com.sytoss.web.dto.MediaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LessonTemplateSaveDTO {

    private String name;

    private  String description;

    private MediaSaveDTO media;

    private Integer duration;

}
