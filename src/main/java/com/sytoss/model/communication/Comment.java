package com.sytoss.model.communication;

import com.sytoss.model.course.Lesson;

import javax.persistence.*;

@Entity
@DiscriminatorValue("13")
public class Comment extends Communication {

    @JoinColumn(name = "lesson_id")
    @ManyToOne(optional = false)
    private Lesson lesson;

    @Column(name = "active")
    private Boolean active;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
