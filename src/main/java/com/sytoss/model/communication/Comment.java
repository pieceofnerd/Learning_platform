package com.sytoss.model.communication;

import com.sytoss.model.course.Lesson;

import javax.persistence.*;

@Entity
@DiscriminatorValue("13")
public class Comment extends Communication {

    @JoinColumn(name = "lesson_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lesson lesson;
}
