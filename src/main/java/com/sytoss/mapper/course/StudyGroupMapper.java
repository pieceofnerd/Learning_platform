package com.sytoss.mapper.course;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.web.dto.StudyGroupDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyGroupMapper extends BaseMapper<StudyGroup, StudyGroupDTO> {
    protected StudyGroupMapper() {
        super(StudyGroup.class, StudyGroupDTO.class);
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
    public List<StudyGroup> toListEntity(List<Object> studyGroupDTOS) {
        return super.toListEntity(studyGroupDTOS);
    }

    @Override
    public List<StudyGroupDTO> toListDTO(List<StudyGroup> studyGroups) {
        return super.toListDTO(studyGroups);
    }
}