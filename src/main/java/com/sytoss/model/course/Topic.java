package com.sytoss.model.course;

import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
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
}
