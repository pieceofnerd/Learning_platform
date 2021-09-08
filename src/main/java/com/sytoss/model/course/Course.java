package com.sytoss.model.course;

import com.sytoss.model.Lookup;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @JoinColumn(name = "course_photo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup coursePhoto;

    @Column(name = "recommended_literature")
    private String recommendedLiterature;

    @JoinColumn(name = "certificate_template_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lookup certificateTemplate;

    @Column
    private Double rating;


    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    public Course() {
    }
}
