package com.sytoss.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDTO extends UserAccountDTO {
    private String bio;
    private List<StudyDTO> studies;
    private List<HomeworkDTO> homeworks;


    public StudentDTO(Long id, String firstName, String secondName, String bio, List<StudyDTO> studies, List<HomeworkDTO> homeworks) {
        super(id, firstName, secondName);
        this.bio = bio;
        this.studies = studies;
        this.homeworks = homeworks;
    }
}