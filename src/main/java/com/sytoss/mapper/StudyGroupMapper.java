package com.sytoss.mapper;

import com.sytoss.model.course.StudyGroup;
import com.sytoss.web.dto.StudyGroupDTO;

import java.util.List;

public class StudyGroupMapper extends BaseMapper<StudyGroup, StudyGroupDTO> {
    StudyGroupMapper(Class<StudyGroup> entityClass, Class<StudyGroupDTO> dtoClass) {
        super(entityClass, dtoClass);
    }

    @Override
    public StudyGroup toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public StudyGroupDTO toDTO(StudyGroup entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<StudyGroup> toListEntity(List<StudyGroupDTO> studyGroupDTOS) {
        return super.toListEntity(studyGroupDTOS);
    }

    @Override
    public List<StudyGroupDTO> toListDTO(List<StudyGroup> studyGroups) {
        return super.toListDTO(studyGroups);
    }
}