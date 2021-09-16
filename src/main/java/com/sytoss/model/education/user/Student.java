package com.sytoss.model.education.user;

import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("3")
public class Student extends UserAccount {

    @Column
    private String bio;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Study> studies;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Homework> homeworks;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "bio='" + bio + '\'' +
                '}';
    }
}
