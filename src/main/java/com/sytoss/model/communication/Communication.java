package com.sytoss.model.communication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @Column(name = "id", nullable = false)
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


}
