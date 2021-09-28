package com.sytoss.model.course;

import com.sytoss.model.communication.Comment;
import com.sytoss.model.education.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "mentor")
    @ManyToOne(optional = false)
    private UserAccount mentor;

    @OneToOne
    @JoinColumn(name = "home_task",referencedColumnName = "id")
    private HomeTask homeTask;

    @JoinColumn(name = "lesson_template")
    @ManyToOne(optional = false)
    private LessonTemplate lessonTemplate;

    @JoinColumn(name = "study_group")
    @ManyToOne(optional = false)
    private StudyGroup studyGroup;

    @Column(name = "lesson_date")
    private Date lessonDate;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();



}
