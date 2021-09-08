package com.sytoss.model.course;

import com.sytoss.model.Lookup;

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
    private Lookup filePath;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @OneToOne(mappedBy = "homeTask")
    private Lesson lesson;

    @OneToMany(mappedBy = "homeTask")
    private List<Homework> homework;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Lookup getFilePath() {
        return filePath;
    }

    public void setFilePath(Lookup filePath) {
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

}
