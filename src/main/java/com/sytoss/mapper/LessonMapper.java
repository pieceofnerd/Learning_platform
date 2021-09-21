package com.sytoss.mapper;

import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.LessonDTO;

import java.util.List;

public class LessonMapper extends BaseMapper<Lesson, LessonDTO> {
    LessonMapper(Class<Lesson> entityClass, Class<LessonDTO> dtoClass) {
        super(entityClass, dtoClass);
    }


    @Override
    public LessonDTO toDTO(Lesson entity) {
        return super.toDTO(entity);
    }

    @Override
    public Lesson toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<Lesson> toListEntity(List<Object> lessonDTOS) {
        return super.toListEntity(lessonDTOS);
    }

    @Override
    public List<LessonDTO> toListDTO(List<Lesson> lessons) {
        return super.toListDTO(lessons);
    }
}