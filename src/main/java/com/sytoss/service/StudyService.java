package com.sytoss.service;

import com.sytoss.exception.NoSuchStudyException;
import com.sytoss.exception.StudyGroupNoContentException;
import com.sytoss.exception.StudyNoContentException;
import com.sytoss.exception.UserAccountNoContentException;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterStudyDTO;

import java.util.List;

public interface StudyService {
    void saveStudy(UserAccount student, StudyGroup studyGroup) throws StudyGroupNoContentException, UserAccountNoContentException;

    void deleteStudy(Study study) throws Exception;

    void updateProgress(UserAccount student, StudyGroup studyGroup) throws StudyNoContentException;

    void updateAssessment(UserAccount student, StudyGroup studyGroup) throws StudyNoContentException;

    Study findStudyById(Long id) throws Exception; // maybe need to delete

    List<Study> findStudiesByFilter(FilterStudyDTO filter) throws Exception;
}
