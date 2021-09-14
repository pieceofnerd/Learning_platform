package com.sytoss.web.dto;


import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class MentorDTO extends UserAccountSaveDTO {
    private String bio;

    private List<Lesson> lessons;

    public MentorDTO(String firstName, String secondName, String bio, List<Lesson> lessons) {
        super(firstName, secondName);
        this.bio = bio;
        this.lessons = lessons;
    }
}