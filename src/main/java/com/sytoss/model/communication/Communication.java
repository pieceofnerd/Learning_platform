package com.sytoss.model.communication;

import com.sytoss.model.education.UserAccount;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "communication_type")
@Table(name = "communication")
public class Communication {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sender_id")
    @ManyToOne(optional = false)
    private UserAccount sender;

    @Column(name = "send_date")
    private Date sendDate = new Date();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "update_date")
    private Date updateDate = new Date();

    @Column(name = "active")
    private Boolean active;

    public Communication() {
    }

    @Transient
    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
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

    public Date getUpdateDate() {
        return updateDate;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
