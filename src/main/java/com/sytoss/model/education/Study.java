package com.sytoss.model.education;


import com.sytoss.model.Media;
import com.sytoss.model.course.StudyGroup;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "study")
public class Study {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "student_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount student;

    @JoinColumn(name = "study_group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private StudyGroup studyGroup;

    @Column(name = "progress", nullable = false)
    private Double progress;

    @Column(name = "assessment")
    private Double assessment;

    @OneToOne
    private Media certificate;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;
}