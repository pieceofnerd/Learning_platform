package com.sytoss.model.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.communication.Message;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.education.UserAccount;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "author_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount author;

    @JoinColumn(name = "homework_state_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup homeworkState;

    @Column(name = "answer_path")
    private String answerPath;

    @Column(name = "fulfillment_date")
    private Date fulfillmentDate;

    @OneToMany(mappedBy = "homework")
    private List<Message> dialog;

    @OneToMany(mappedBy = "homework")
    private List<Feedback> feedbacks;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public List<Message> getDialog() {
        return dialog;
    }

    public void setDialog(List<Message> dialog) {
        this.dialog = dialog;
    }

    @JoinColumn(name = "home_task_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HomeTask homeTask;

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    public Lookup getHomeworkState() {
        return homeworkState;
    }

    public void setHomeworkState(Lookup homeworkState) {
        this.homeworkState = homeworkState;
    }

    public Date getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(Date fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public HomeTask getHomeTask() {
        return homeTask;
    }

    public void setHomeTask(HomeTask homeTask) {
        this.homeTask = homeTask;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", author=" + author +
                ", homeworkState=" + homeworkState +
                ", answerPath='" + answerPath + '\'' +
                ", fulfillmentDate=" + fulfillmentDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", homeTask=" + homeTask +
                '}';
    }
}
