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

}
