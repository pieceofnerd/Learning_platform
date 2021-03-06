package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_contet_exception.StudyNoContentException;
import com.sytoss.exception.no_contet_exception.UserAccountNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchStudyException;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterStudyDTO;

import java.util.List;

public interface StudyService {
    void saveStudy(UserAccount student, StudyGroup studyGroup) throws StudyGroupNoContentException, UserAccountNoContentException;

    void deleteStudy(Study study) throws StudyNoContentException, NoSuchStudyException;

    void updateProgress(UserAccount student, StudyGroup studyGroup) throws StudyNoContentException, UserAccountNoContentException, StudyGroupNoContentException;

    void updateAssessment(UserAccount student, StudyGroup studyGroup) throws StudyNoContentException, UserAccountNoContentException, StudyGroupNoContentException;

    Study findStudyById(Long id) throws Exception; // maybe need to delete

    List<Study> findStudiesByFilter(FilterStudyDTO filter);
}
