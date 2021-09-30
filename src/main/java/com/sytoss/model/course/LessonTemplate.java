package com.sytoss.model.course;

import com.sytoss.model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "lesson_template")
public class LessonTemplate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media")
    private Media media;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne()
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "lessonTemplate")
    private List<Lesson> lessons;

}
