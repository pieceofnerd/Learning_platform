package com.sytoss.model.course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<LessonTemplate> getActiveLessonTemplates() {
        List<LessonTemplate> activeLessonTemplates = new ArrayList<>();
        for (LessonTemplate lessonTemplate : getLessonTemplates()) {
            if (lessonTemplate.getActive())
                activeLessonTemplates.add(lessonTemplate);
        }
        return activeLessonTemplates;
    }


    public void setLessonTemplates(List<LessonTemplate> lessonTemplates) {
        this.lessonTemplates = lessonTemplates;
    }
}
