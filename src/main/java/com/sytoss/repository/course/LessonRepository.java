package com.sytoss.repository.course;

import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l left  join  fetch  l.comments where l.id = ?1")
    Lesson findById(Long id);

    @Query("select l from Lesson l where l.active = true and l.lessonTemplate = ?1 and l.studyGroup = ?2")
    List<Lesson> findLessonsByActiveIsTrueAndLessonTemplateAndStudyGroup(LessonTemplate lessonTemplate, StudyGroup studyGroup);

    @Query("select l from Lesson l where l.active = true and l.studyGroup = ?1")
    List<Lesson> findLessonsByActiveIsTrueAndStudyGroup(StudyGroup studyGroup);

    @Query("select l from Lesson l where l.studyGroup=?1 and l.lessonDate>=?2 and l.lessonDate<=?3")
    List<Lesson> findLessonsByTimePeriodAndStudyGroup(StudyGroup studyGroup, Date startTime, Date endTime);

    @Query("select l from Lesson l where l.studyGroup = ?1 and l.lessonDate>CURRENT_TIMESTAMP")
    List<Lesson> findFutureLessonsByStudyGroup(StudyGroup studyGroup);

    @Query("select l from Lesson l where l.lessonTemplate = ?1")
    List<Lesson> findLessonByLessonTemplate(LessonTemplate lessonTemplate);

    @Query("select l from Lesson l where l.mentor = ?1")
    List<Lesson> findLessonByMentor(UserAccount mentor);

}
