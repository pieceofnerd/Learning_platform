package com.sytoss.controller;

import com.sytoss.exception.NoSuchStudyException;
import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.mapper.StudyMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.service.StudyService;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyMapper studyMapper;
    private final UserAccountMapper userAccountMapper;
    private final StudyGroupMapper studyGroupMapper;

    public void saveStudy(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        final UserAccount student = userAccountMapper.toEntity(studentDTO);
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        studyService.saveStudy(student, studyGroup);
    }

    public void deleteStudy(StudyDTO studyDTO) {
        final Study study = studyMapper.toEntity(studyDTO);
        try {
            studyService.deleteStudy(study);
        } catch (NoSuchStudyException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessment(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        final UserAccount student = userAccountMapper.toEntity(studentDTO);
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studyService.updateAssessment(student, studyGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProgress(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        final UserAccount student = userAccountMapper.toEntity(studentDTO);
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studyService.updateProgress(student, studyGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<StudyDTO> findStudiesByFilter(FilterStudyDTO filter) throws Exception {
        final List<Study> studies = studyService.findStudiesByFilter(filter);
        return studyMapper.toListDTO(studies);
    }
}