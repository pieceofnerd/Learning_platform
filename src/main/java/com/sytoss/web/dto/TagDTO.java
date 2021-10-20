package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Long id;

    private UserAccountDTO mentor;

    private UserAccountDTO student;

    private CourseDTO course;

    private LookupDTO tag;
}
