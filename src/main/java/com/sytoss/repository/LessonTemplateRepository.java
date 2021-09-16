package com.sytoss.repository;

import com.sytoss.model.course.LessonTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LessonTemplateRepository  extends JpaRepository<LessonTemplate, Long> {
}
