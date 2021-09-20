package com.sytoss.repository.course;

import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l where l.active = true and l.lessonTemplate = ?1 and l.studyGroup = ?2")
    List<Lesson>  findLessonsByActiveIsTrueAndLessonTemplateAndStudyGroup(LessonTemplate lessonTemplate, StudyGroup studyGroup);

    @Query("select l from Lesson l where l.active = true and l.studyGroup = ?1")
    List<Lesson> findLessonsByActiveIsTrueAndStudyGroup(StudyGroup studyGroup);
}
