package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthday;
    private String bio;
    private AddressDTO address;
    private String email;
    private MediaDTO photo;
    private LookupDTO studentStatus;
    private List<StudyDTO> studies;
    private List<HomeworkDTO> homeworks;
    private List<LessonDTO> lessons;
    private boolean deleted;
    private Date lastActivity;
    private Date createdDate;
    private Date updatedDate;

}