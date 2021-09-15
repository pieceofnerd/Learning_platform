package com.sytoss.service.impl;

import com.sytoss.mapper.StudyMapper;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.StudyRepository;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.FilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserAccountService userAccountService;
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
    public List<Study> findStudiesByFilter(FilterDTO filter) throws Exception {
        Student student = (Student) userAccountService.findUserAccountById(filter.getStudent());
        return student.getStudies();
    }
}