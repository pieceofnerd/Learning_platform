package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationDTO {
    private Long id;
    private UserAccountDTO sender;
    private UserAccountDTO receiver;
    private Date sendDate;
    private String content;
    private LessonDTO lesson;
    private HomeworkDTO homework;
    private Integer score;
    private Boolean active;
    private Date updateDate;
}