package com.sytoss.web.dto;

import com.sytoss.model.Media;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.education.Address;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import lombok.*;

import java.util.Arrays;
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
    private Address address;
    private String email;
    private Media photo;
    private List<Study> studies;
    private List<Homework> homeworks;
    private List<Lesson> lessons;
    private boolean deleted;
    private Long role;
    private Date lastActivity;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public String toString() {
        return "\nUserAccountDTO{" +
                "\n  id=" + id +
                "\n, firstName='" + firstName + '\'' +
                "\n, secondName='" + secondName + '\'' +
                "\n, birthday=" + birthday +
                "\n, email='" + email + '\'' +
                "\n, deleted=" + deleted +
                "\n, role=" + role +
                "\n, lastActivity=" + lastActivity +
                "\n, studies=" + studies +
                "\n, homeworks=" + homeworks +
                "\n}";
    }
}