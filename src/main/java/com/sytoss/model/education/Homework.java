package com.sytoss.model.education;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.Lookup;
import com.sytoss.model.communication.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "author_id")
    @ManyToOne(optional = false)
    private UserAccount author;

    @JoinColumn(name = "homework_state_id")
    @ManyToOne(optional = false)
    private Lookup homeworkState;

    @Column(name = "answer_path")
    private String answerPath;

    @Column(name = "fulfillment_date")
    private Date fulfillmentDate;

    @OneToMany(mappedBy = "homework", fetch = FetchType.LAZY)
    private List<Message> dialog;

    @OneToOne(mappedBy = "homework", fetch = FetchType.LAZY)
    private Feedback feedback;

    @Column(name = "active")
    @ColumnDefault(value = "1")
    private boolean isActive;

    @JoinColumn(name = "home_task_id")
    @ManyToOne(optional = false)
    private HomeTask homeTask;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

}
