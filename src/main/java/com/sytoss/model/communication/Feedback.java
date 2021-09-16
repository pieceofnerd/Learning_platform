package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;

import javax.persistence.*;

@Entity
@DiscriminatorValue("12")
public class Feedback extends Communication {

    @JoinColumn(name = "homework_id")
    @ManyToOne(optional = false)
    private Homework homework;

    @Column
    private Integer score;

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
