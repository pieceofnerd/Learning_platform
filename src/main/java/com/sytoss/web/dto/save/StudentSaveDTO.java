package com.sytoss.web.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class StudentSaveDTO extends UserAccountDTO {
    private String bio;
    private List<StudyDTO> studies;
    private List<HomeworkDTO> homeworks;

    public StudentSaveDTO(String firstName, String secondName, String bio, List<StudyDTO> studies, List<HomeworkDTO> homeworks) {
        super(firstName, secondName);
        this.bio = bio;
        this.studies = studies;
        this.homeworks = homeworks;
    }
}