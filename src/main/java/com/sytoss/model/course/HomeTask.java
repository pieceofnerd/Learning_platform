package com.sytoss.model.course;

import com.sytoss.model.Media;
import com.sytoss.model.education.Homework;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "home_task")
public class HomeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String task;

    @JoinColumn(name = "file_path")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media filePath;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @OneToOne(mappedBy = "homeTask")
    private Lesson lesson;

    @OneToMany(mappedBy = "homeTask")
    private List<Homework> homework;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Media getFilePath() {
        return filePath;
    }

    public void setFilePath(Media filePath) {
        this.filePath = filePath;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
    }
}
