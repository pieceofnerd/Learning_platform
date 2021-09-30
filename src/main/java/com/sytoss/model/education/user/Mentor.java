package com.sytoss.model.education.user;

import com.sytoss.model.course.Lesson;
import com.sytoss.model.education.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("2")
public class Mentor extends UserAccount {

    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private List<Lesson> lessons;

}
