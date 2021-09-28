package com.sytoss.web.dto.save;

import com.sytoss.web.dto.HomeTaskDTO;
import com.sytoss.web.dto.LessonTemplateDTO;
import com.sytoss.web.dto.StudyGroupDTO;
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
public class LessonSaveDTO {

    private UserAccountDTO mentor;

    private HomeTaskSaveDTO homeTask;

    private LessonTemplateDTO lessonTemplate;

    private StudyGroupDTO studyGroup;

    private Date lessonDate;
}