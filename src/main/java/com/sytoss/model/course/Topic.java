package com.sytoss.model.course;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<LessonTemplate> lessonTemplates;

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<LessonTemplate> getLessonTemplates() {
        return lessonTemplates;
    }

    public void setLessonTemplates(List<LessonTemplate> lessonTemplates) {
        this.lessonTemplates = lessonTemplates;
    }
}
