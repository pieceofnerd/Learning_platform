package com.sytoss.web.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class StudentSaveDTO {
    private String bio;
    private List<StudyDTO> studies;
    private List<HomeworkDTO> homeworks;


}