package com.sytoss.repository.course;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l left  join  fetch  l.comments where l.id = ?1")
    Lesson findById(Long id);

}
