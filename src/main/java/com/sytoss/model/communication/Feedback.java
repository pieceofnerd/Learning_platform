package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@DiscriminatorValue("12")
public class Feedback extends Communication {

    @OneToOne(optional = false)
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @Column(name = "score")
    private Integer score;

}
