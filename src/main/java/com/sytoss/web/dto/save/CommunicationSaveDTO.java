package com.sytoss.web.dto.save;

import com.sytoss.web.dto.HomeworkDTO;
import com.sytoss.web.dto.LessonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationSaveDTO {

    private Long sender;

    private Long receiver;

    private String content;

    private LessonDTO lesson;

    private HomeworkDTO homework;

    private Integer score;

    private Boolean active;
}