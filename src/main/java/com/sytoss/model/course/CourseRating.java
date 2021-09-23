package com.sytoss.model.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "course_rating")
public class CourseRating {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(optional = false)
    private Course course;

    @Column(name = "rating")
    private Double rating;


}
