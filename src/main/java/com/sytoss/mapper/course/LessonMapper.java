package com.sytoss.mapper.course;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.LessonDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonMapper extends BaseMapper<Lesson, LessonDTO> {
    protected LessonMapper() {
        super(Lesson.class, LessonDTO.class);
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