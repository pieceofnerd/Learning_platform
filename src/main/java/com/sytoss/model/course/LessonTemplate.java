package com.sytoss.model.course;

import com.sytoss.model.Media;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson_template")
public class LessonTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @JoinColumn(name = "media")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Media media;

    @Column
    private Integer duration;

    @JoinColumn(name = "topic_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Topic topic;

    @OneToMany(mappedBy = "lessonTemplate")
    private List<Lesson> lessons;

    public LessonTemplate() {
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
