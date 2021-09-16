package com.sytoss.mapper;

import com.sytoss.model.course.Topic;
import com.sytoss.model.education.Study;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.TopicDTO;
import com.sytoss.web.dto.save.PriceSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyMapper extends BaseMapper<Study,StudyDTO>{

    @Autowired
    public StudyMapper(ModelMapper mapper) {
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
    public List<Study> toListEntity(List<StudyDTO> studyDTOS) {
        return super.toListEntity(studyDTOS);
    }

    @Override
    public List<StudyDTO> toListDTO(List<Study> studies) {
        return super.toListDTO(studies);
    }
}