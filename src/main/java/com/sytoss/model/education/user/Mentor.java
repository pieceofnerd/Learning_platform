package com.sytoss.model.education.user;

import com.sytoss.model.course.Lesson;
import com.sytoss.model.education.UserAccount;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Mentor extends UserAccount {

    @Column
    private String bio;

    @OneToMany(mappedBy = "mentor")
    private List<Lesson> lessons;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "bio='" + bio + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
