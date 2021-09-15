package com.sytoss.web.dto;


import com.sytoss.model.course.Lesson;
import lombok.Getter;

import java.util.List;

@Getter
public class MentorDTO {
    private String bio;

    private List<Lesson> lessons;

}