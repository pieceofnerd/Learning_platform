package com.sytoss.mapper;

import com.sytoss.model.course.LessonTemplate;
import com.sytoss.web.dto.LessonTemplateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LessonTemplateMapper extends BaseMapper<LessonTemplate, LessonTemplateDTO> {
    protected LessonTemplateMapper(ModelMapper mapper) {
        super(LessonTemplate.class, LessonTemplateDTO.class);
    }

    @Override
    public LessonTemplate toEntity(LessonTemplateDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public LessonTemplateDTO toDTO(LessonTemplate entity) {
        return super.toDTO(entity);
    }
}