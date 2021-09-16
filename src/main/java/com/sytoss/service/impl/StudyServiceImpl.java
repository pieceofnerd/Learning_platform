package com.sytoss.service.impl;

import com.sytoss.mapper.StudyMapper;
import com.sytoss.model.education.Study;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserAccountService userAccountService;
    private final StudyGroupRepository studyGroupRepository;
    private final StudyMapper studyMapper;

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
        if (!studyRepository.exists(id)) {
            throw new Exception("Study with id = " + id + " not found!");
        }
        Study study = studyRepository.findOne(id);
        return study;
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }

    @Override
    public List<Study> findStudiesByFilter(FilterStudyDTO filter) throws Exception {
        final Filter f = filter.getFilter();
        List<Study> studies = new ArrayList<>();
        if (f == Filter.ID) {
            studies.add(studyRepository.findStudyById(filter.getId()));
        }
        if (f == Filter.STUDENT) {
            studies.addAll(studyRepository.findStudiesByStudent(userAccountService.findUserAccountById(filter.getStudent())));
        }
        if (f == Filter.STUDY_GROUP) {
            studies.addAll(studyRepository.findStudiesByStudyGroup(studyGroupRepository.findOne(filter.getStudyGroup())));
        }
        return studies;
    }
}