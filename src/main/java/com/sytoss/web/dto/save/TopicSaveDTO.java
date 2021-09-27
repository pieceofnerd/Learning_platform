package com.sytoss.web.dto.save;

import com.sytoss.web.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicSaveDTO {

    private String name;

    private String description;

    private CourseDTO course;


}
