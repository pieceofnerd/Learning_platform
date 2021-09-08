package com.sytoss.model.course;

import com.sytoss.model.Lookup;
import com.sytoss.model.education.UserAccount;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "fulfillment_date")
    private Date fulfillmentDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;


    @JoinColumn(name = "home_task_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HomeTask homeTask;
}