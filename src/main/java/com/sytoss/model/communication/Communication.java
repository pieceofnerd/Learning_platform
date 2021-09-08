package com.sytoss.model.communication;

import com.sytoss.model.Lookup;
import com.sytoss.model.education.UserAccount;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "sender_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount sender;

    @Column(name = "send_date", nullable = false)
    private Date sendDate;

    @Column(name = "content", nullable = false)
    @NotBlank
    private String content;

    @JoinColumn(name = "communication_type", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup lookup;

    @JoinColumn(name = "receiver_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount receiver;

    @Column(name = "lesson_id")
    private Long lesson;

    @Column(name = "homework_id")
    private Long homework;

    @Column(name = "update_date")
    private Date updateDate;

    public Communication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }

    public Long getHomework() {
        return homework;
    }

    public void setHomework(Long homework) {
        this.homework = homework;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
