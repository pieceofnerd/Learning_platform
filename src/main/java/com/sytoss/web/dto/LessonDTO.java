package com.sytoss.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private Long id;

    private UserAccountDTO mentor;

    private HomeTaskDTO homeTask;

    private LessonTemplateDTO lessonTemplate;

    private StudyGroupDTO studyGroup;

    private List<CommunicationDTO> comments;

    private Date lessonDate;

    private Date createdDate;

    private Date updatedDate;
}