package com.sytoss.mapper;

import com.sytoss.model.course.LessonTemplate;
import com.sytoss.web.dto.save.LessonTemplateSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LessonTemplateMapper extends BaseMapper<LessonTemplate, LessonTemplateSaveDTO> {
    protected LessonTemplateMapper(ModelMapper mapper) {
        super(LessonTemplate.class, LessonTemplateSaveDTO.class);
    }

    @Override
    public LessonTemplate toEntity(LessonTemplateSaveDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public LessonTemplateSaveDTO toDTO(LessonTemplate entity) {
        return super.toDTO(entity);
    }
}
