package com.sytoss.service.impl;

import com.sytoss.model.education.Study;
import com.sytoss.repository.StudyRepository;
import com.sytoss.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {


    private final StudyRepository studyRepository;

    @Override
    public boolean saveStudy(Study study) {
        if (study == null)
            return false;
        studyRepository.save(study);
        return true;
    }

    @Override
    public boolean updateStudy(Long id, Study study) {
        return false;
    }

    @Override
    public boolean deleteStudy(Study study) {
        return false;
    }

    @Override
    public Study findStudyById(Long id) throws Exception {
        if (!studyRepository.existsById(id)) {
            throw new Exception("Study with id = " + id + " not found!");
        }
        Study study = studyRepository.getOne(id);
        return study;
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}