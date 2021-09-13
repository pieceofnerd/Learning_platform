package com.sytoss.service;

import com.sytoss.model.education.Study;

import java.util.List;

public interface StudyService {
    boolean saveStudy(Study study);

    boolean updateStudy(Long id, Study study);

    boolean deleteStudy(Study study);

    Study findStudyById(Long id) throws Exception;

    List<Study> findAll();
}
