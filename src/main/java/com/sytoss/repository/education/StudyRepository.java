package com.sytoss.repository.education;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study,Long> {

    List<Study> findStudiesByDeletedIsFalseAndStudent(UserAccount student);

    List<Study> findStudiesByDeletedIsFalseAndStudyGroup(StudyGroup studyGroup);


    Study findStudyByDeletedIsFalseAndStudentAndStudyGroup(UserAccount student, StudyGroup studyGroup);


}
