package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;

import javax.persistence.*;

@Entity
@DiscriminatorValue("14")
public class Message extends Communication {

    @JoinColumn(name = "receiver_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount receiver;

    @JoinColumn(name = "homework_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Homework homework;

}
