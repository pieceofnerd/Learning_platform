package com.sytoss.web.dto;


import com.sytoss.model.course.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO extends UserAccountDTO{
    private String bio;

    private List<Lesson> lessons;

}