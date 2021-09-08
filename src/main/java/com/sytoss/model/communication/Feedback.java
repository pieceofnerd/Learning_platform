package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;

import javax.persistence.*;

@Entity
@DiscriminatorValue("12")
public class Feedback extends Communication {

    @JoinColumn(name = "homework_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Homework homework;

    @Column
    private Integer score;

}
