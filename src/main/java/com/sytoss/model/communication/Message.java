package com.sytoss.model.communication;

import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;

import javax.persistence.*;

@Entity
@DiscriminatorValue("14")
public class Message extends Communication {

    @JoinColumn(name = "receiver_id")
    @ManyToOne(optional = false)
    private UserAccount receiver;

    @JoinColumn(name = "homework_id")
    @ManyToOne(optional = false)
    private Homework homework;

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
