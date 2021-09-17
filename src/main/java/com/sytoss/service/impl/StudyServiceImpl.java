package com.sytoss.service.impl;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.StudyGroupService;
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
@Transactional
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserAccountService userAccountService;
    private final StudyGroupService studyGroupService;


    @Override
    public boolean saveStudy(UserAccount student, StudyGroup studyGroup) {
        if (student == null)
            return false;
        if (studyGroup == null)
            return false;

        Study study = new Study();
        study.setStudent(student);
        study.setStudyGroup(studyGroup);
        studyRepository.save(study);
        return true;
    }

    @Override
    public boolean deleteStudy(Study study) {
        if (study == null)
            return false;
        if (!studyRepository.exists(study.getId()))
            return false;
        study.setDeleted(true);
        studyRepository.save(study);
        return true;
    }

    @Override
    public boolean updateProgress(UserAccount student, StudyGroup studyGroup) {
        //TODO
        return false;
    }

    @Override
    @Transactional
    public void updateAssessment(UserAccount student, StudyGroup studyGroup) {
        final Study study = studyRepository.findOne(student.getId());
        for (Study studyGroupStudy : studyGroup.getStudies()) {

        }
//        studyRepository.save(study);
        //TODO
    }

    @Override
    public Study findStudyById(Long id) throws Exception {
        if (!studyRepository.exists(id))
            throw new Exception("Study with id = " + id + " not found!");
        return studyRepository.findOne(id);
    }

    @Override
    public List<Study> findStudiesByFilter(FilterStudyDTO filter) throws Exception {
        List<Study> studies = new ArrayList<>();
        switch (filter.getFilter()) {
            case STUDENT: {
                studies.addAll(studyRepository.findStudiesByStudent(userAccountService.findUserAccountById(filter.getStudent())));
                break;
            }
            case STUDY_GROUP: {
                studies.addAll(studyRepository.findStudiesByStudyGroup(studyGroupService.findStudyGroupById(filter.getStudyGroup())));
                break;
            }
        }
        return studies;
    }
}