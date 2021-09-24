package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {

    @Query("select s from StudyGroup s left join fetch s.studies where s.id = ?1")
    StudyGroup findById(Long id);


    @Query("select s from StudyGroup s where s.course = ?1 and s.deleted = false")
    List<StudyGroup> findStudyGroupsByCourseAndDeletedIsFalse(Course course);

    @Query("select s from StudyGroup s where s.deleted = ?1")
    List<StudyGroup> findStudyGroupsByDeleted(boolean deleted);

}
