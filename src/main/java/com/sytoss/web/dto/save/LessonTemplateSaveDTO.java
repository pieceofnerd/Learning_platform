package com.sytoss.web.dto.save;

import com.sytoss.web.dto.TopicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonTemplateSaveDTO {

    private String name;

    private  String description;

    private MediaSaveDTO media;

    private Integer duration;

}
