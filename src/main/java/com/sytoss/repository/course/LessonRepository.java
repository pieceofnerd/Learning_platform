package com.sytoss.repository.course;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
