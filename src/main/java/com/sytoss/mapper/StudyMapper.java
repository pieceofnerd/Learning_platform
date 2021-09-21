package com.sytoss.mapper;

import com.sytoss.model.education.Study;
import com.sytoss.web.dto.StudyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyMapper extends BaseMapper<Study,StudyDTO>{


    public StudyMapper() {
        super(Study.class, StudyDTO.class);
    }

    @Override
    public Study toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public StudyDTO toDTO(Study entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Study> toListEntity(List<Object> studyDTOS) {
        return super.toListEntity(studyDTOS);
    }

    @Override
    public List<StudyDTO> toListDTO(List<Study> studies) {
        return super.toListDTO(studies);
    }
}