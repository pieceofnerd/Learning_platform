package com.sytoss.web.dto;


import com.sytoss.model.course.Lesson;
import lombok.Getter;

import java.util.List;

@Getter
public class MentorDTO extends UserAccountDTO {
    private String bio;

    private List<Lesson> lessons;

    public MentorDTO(String firstName, String secondName, String bio, List<Lesson> lessons) {
        super(firstName, secondName);
        this.bio = bio;
        this.lessons = lessons;
    }
}