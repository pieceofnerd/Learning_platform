package com.sytoss.web.dto.save;

import com.sytoss.web.dto.HomeworkDTO;
import com.sytoss.web.dto.LessonDTO;
import com.sytoss.web.dto.UserAccountDTO;
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

    private UserAccountDTO sender;

    private UserAccountDTO receiver;

    private String content;

    private LessonDTO lesson;

    private HomeworkDTO homework;

    private Integer score;

    private Boolean active = true;
}