package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudyGroupRepository extends JpaRepository<StudyGroup,Long> {

    @Query("select s from StudyGroup s where s.course = ?1")
    List<StudyGroup> findStudyGroupsByCourse(Course course);

    @Query("select s from StudyGroup s where s.deleted = ?1")
    List<StudyGroup> findStudyGroupsByDeleted(boolean deleted);

}
