package com.sytoss.web.dto;

import com.sytoss.model.Media;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyDTO {

    private UserAccountDTO student;

    private StudyGroupDTO studyGroup;

    private Double progress;

    private Double assessment;

    private MediaDTO certificate;

    private Date createdDate;

    private Date updatedDate;
}