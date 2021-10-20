package com.sytoss.controller;

import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.mapper.course.CourseMapper;
import com.sytoss.mapper.course.StudyGroupMapper;
import com.sytoss.mapper.education.UserAccountMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.service.StudyGroupService;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.filter.FilterStudyGroupDTO;
import com.sytoss.web.dto.save.StudyGroupSaveDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudyGroupController {
    private static final Logger logger = LoggerFactory.getLogger(StudyGroupController.class);
    private final StudyGroupService studyGroupService;
    private final StudyGroupMapper studyGroupMapper;
    private final CourseMapper courseMapper;
    private final UserAccountMapper userAccountMapper;

    public void createStudyGroup(StudyGroupSaveDTO studyGroupSaveDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupSaveDTO);
        try {
            studyGroupService.createStudyGroup(studyGroup);
        } catch (StudyGroupNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void updateStudyGroup(StudyGroupDTO studyGroupSaveDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupSaveDTO);
        try {
            studyGroupService.updateStudyGroup(studyGroup);
        } catch (NoSuchStudyGroupException | StudyGroupNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteStudyGroup(StudyGroupDTO studyGroupDTO) {
        final StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studyGroupService.deleteStudyGroup(studyGroup);
        } catch (NoSuchStudyGroupException e) {
            logger.error(e.getMessage());
        }
    }


    public List<StudyGroupDTO> findStudyGroupsByFilter(FilterStudyGroupDTO filter) {
        final List<StudyGroupDTO> studyGroupDTOList = new ArrayList<>();

        studyGroupDTOList.addAll(studyGroupMapper.toListDTO(studyGroupService.findStudyGroupsByFilter(filter)));
        return studyGroupDTOList;
    }
}