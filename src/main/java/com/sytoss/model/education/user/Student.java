package com.sytoss.model.education.user;

import com.sytoss.model.Lookup;
import com.sytoss.model.Tag;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
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
@DiscriminatorValue("3")
public class Student extends UserAccount {

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_status")
    private Lookup studentStatus;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Study> studies;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Homework> homeworks;

    @OneToMany(mappedBy = "student",
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    private List<Tag> tags;
}
