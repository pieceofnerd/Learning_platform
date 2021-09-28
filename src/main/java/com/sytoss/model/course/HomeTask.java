package com.sytoss.model.course;

import com.sytoss.model.Media;
import com.sytoss.model.education.Homework;
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
@NoArgsConstructor

@Entity
@Table(name = "home_task")
public class HomeTask {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task")
    private String task;

    @ManyToOne(optional = false)
    @JoinColumn(name = "file_path")
    private Media filePath;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @OneToOne(mappedBy = "homeTask")
    private Lesson lesson;

    @OneToMany(mappedBy = "homeTask",fetch = FetchType.LAZY)
    private List<Homework> homeworks;

}
