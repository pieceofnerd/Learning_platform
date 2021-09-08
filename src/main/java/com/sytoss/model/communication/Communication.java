package com.sytoss.model.communication;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "send_date", nullable = false)
    private Date sendDate;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "communication_type", nullable = false)
    private Long communicationTypeId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "homework_id")
    private Long homeworkId;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
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

    public Long getCommunicationTypeId() {
        return communicationTypeId;
    }

    public void setCommunicationTypeId(Long communicationTypeId) {
        this.communicationTypeId = communicationTypeId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
