package com.sytoss.controller;

import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.service.StudyGroupService;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import com.sytoss.web.dto.save.StudyGroupSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;
    private final StudyGroupMapper studyGroupMapper;
    private final UserAccountMapper userAccountMapper;

    public void createStudyGroup(StudyGroupSaveDTO studyGroupSaveDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupSaveDTO);
        studyGroupService.createStudyGroup(studyGroup);
    }

    public void updateStudyGroup(StudyGroupDTO studyGroupSaveDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupSaveDTO);
        studyGroupService.updateStudyGroup(studyGroup);
    }

    public void deleteStudyGroup(StudyGroupDTO studyGroupDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        studyGroupService.deleteStudyGroup(studyGroup);
    }


    public List<UserAccountDTO> findStudentsByStudyGroup(StudyGroupDTO studyGroupDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);

        List<UserAccountDTO> userAccounts = userAccountMapper.toListDTO(studyGroupService.findStudentsByStudyGroup(studyGroup));
        return userAccounts;
    }

    public List<StudyGroupDTO> findStudyGroupsByFilter(FilterStudyGroupDTO filter) {
        final List<StudyGroupDTO> studyGroupDTOList = new ArrayList<>();
        try {
            studyGroupDTOList.addAll(studyGroupMapper.toListDTO(studyGroupService.findStudyGroupsByFilter(filter)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyGroupDTOList;
    }
}