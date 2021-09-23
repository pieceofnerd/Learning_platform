package com.sytoss.service;

import com.sytoss.exception.NoSuchStudyGroupException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;

import java.util.List;

public interface StudyGroupService {
    void createStudyGroup(StudyGroup studyGroup);

    void updateStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException;

    void deleteStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException;

    List<StudyGroup> findStudyGroupsByCourse(Course course) throws Exception;

    void updateFreePlaceNumber(StudyGroup studyGroup) throws Exception;

    List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup);

    List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter);
}
