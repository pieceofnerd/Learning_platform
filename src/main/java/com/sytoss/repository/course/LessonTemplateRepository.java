package com.sytoss.repository.course;

import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonTemplateRepository  extends JpaRepository<LessonTemplate, Long> {


    //this method should be deleted
    List<LessonTemplate> findLessonTemplatesByActiveTrueAndTopic(Topic topic);
}
