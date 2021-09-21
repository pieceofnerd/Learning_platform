package com.sytoss.mapper;

import com.sytoss.model.course.LessonTemplate;
import com.sytoss.web.dto.LessonTemplateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonTemplateMapper extends BaseMapper<LessonTemplate, LessonTemplateDTO> {

    protected LessonTemplateMapper() {
        super(LessonTemplate.class, LessonTemplateDTO.class);
    }

    @Override
    public LessonTemplate toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public LessonTemplateDTO toDTO(LessonTemplate entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<LessonTemplate> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<LessonTemplateDTO> toListDTO(List<LessonTemplate> lessonTemplates) {
        return super.toListDTO(lessonTemplates);
    }
}
