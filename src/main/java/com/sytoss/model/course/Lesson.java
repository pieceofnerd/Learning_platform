package com.sytoss.model.course;

import com.sytoss.model.communication.Comment;
import com.sytoss.model.education.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_task", referencedColumnName = "id")
    private HomeTask homeTask;

    @JoinColumn(name = "lesson_template")
    @ManyToOne(optional = false)
    private LessonTemplate lessonTemplate;

    @JoinColumn(name = "study_group")
    @ManyToOne(optional = false)
    private StudyGroup studyGroup;

    @Column(name = "lesson_date")
    private Date lessonDate;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column(name = "active")
    private Boolean active;

    public Lesson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getMentor() {
        return mentor;
    }

    public void setMentor(UserAccount mentor) {
        this.mentor = mentor;
    }

    public HomeTask getHomeTask() {
        return homeTask;
    }

    public void setHomeTask(HomeTask homeTask) {
        this.homeTask = homeTask;
    }

    public LessonTemplate getLessonTemplate() {
        return lessonTemplate;
    }

    public void setLessonTemplate(LessonTemplate lessonTemplate) {
        this.lessonTemplate = lessonTemplate;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "\nLesson{" +
                "\n  id=" + id +
                "\n, mentor=" + mentor.getId() +
                "\n, homeTask=" + homeTask.getId() +
                "\n, studyGroup=" + studyGroup.getId() +
                "\n, lessonDate=" + lessonDate +
                "\n, active=" + active +
                "\n, comments=" + comments +
                "\n}";
    }
}
