package com.sytoss.model.course;

import com.sytoss.model.education.UserAccount;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "mentor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount mentor;

    @OneToOne(mappedBy = "lesson")
    private HomeTask homeTask;

    @JoinColumn(name = "lesson_template")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LessonTemplate lessonTemplate;

    @JoinColumn(name = "study_group")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StudyGroup studyGroup;

    @Column(name = "lesson_date")
    private Date lessonDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Lesson() {
    }
}
