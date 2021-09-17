package com.sytoss.service;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterStudyDTO;

import java.util.List;

public interface StudyService {
    boolean saveStudy(UserAccount student, StudyGroup studyGroup);

    boolean deleteStudy(Study study);

    boolean updateProgress(UserAccount student, StudyGroup studyGroup);

    void updateAssessment(UserAccount student, StudyGroup studyGroup);

    Study findStudyById(Long id) throws Exception;

    List<Study> findStudiesByFilter(FilterStudyDTO filter) throws Exception;
}
