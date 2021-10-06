package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.CourseNoContentException;
import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;

import java.util.List;

public interface StudyGroupService {
    void createStudyGroup(StudyGroup studyGroup) throws StudyGroupNoContentException;

    void updateStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException, StudyGroupNoContentException;

    void deleteStudyGroup(StudyGroup studyGroup) throws NoSuchStudyGroupException;

    List<StudyGroup> findStudyGroupsByCourse(Course course) throws CourseNoContentException;

    void updateFreePlaceNumber(StudyGroup studyGroup) throws StudyGroupNoContentException;

    List<StudyGroup> findStudyGroupsByFilter(FilterStudyGroupDTO filter);
}
