package com.sytoss.repository.education;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study,Long> {

    @Query("select s from Study s where s.student = ?1")
    List<Study> findStudiesByStudent(UserAccount student);

    @Query("select s from Study s where s.studyGroup = ?1")
    List<Study> findStudiesByStudyGroup(StudyGroup studyGroup);

    @Query("select s from Study s where s.student = ?1 and s.studyGroup = ?2")
    Study findStudyByStudentAndStudyGroup(UserAccount student, StudyGroup studyGroup);
}
