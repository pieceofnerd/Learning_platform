package com.sytoss.model.communication;

import com.sytoss.model.course.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@DiscriminatorValue("13")
public class Comment extends Communication {

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "active")
    private Boolean active;

}
