package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
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
@DiscriminatorValue("14")
public class Message extends Communication {

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_id")
    private UserAccount receiver;

    @ManyToOne(optional = false)
    @JoinColumn(name = "homework_id")
    private Homework homework;
}
