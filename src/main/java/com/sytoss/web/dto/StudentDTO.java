package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends UserAccountDTO {
    private String bio;
    private List<StudyDTO> studies;
    private List<HomeworkDTO> homeworks;

}