package com.sytoss.model.course;

import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;

    public Category() {
    }
}
