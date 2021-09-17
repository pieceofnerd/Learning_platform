package com.sytoss.service;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;

import java.util.List;

public interface StudyGroupService {
    boolean createStudyGroup(StudyGroup studyGroup);

    boolean updateStudyGroup(StudyGroup studyGroup);

    boolean deleteStudyGroup(StudyGroup studyGroup);

    StudyGroup findStudyGroupById(Long id) throws Exception;

    List<StudyGroup> findStudyGroupsByCourse(Course course) throws Exception;

    List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup);

    List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter);
}
