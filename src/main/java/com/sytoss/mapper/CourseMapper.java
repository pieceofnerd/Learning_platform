package com.sytoss.mapper;

import com.sytoss.model.course.Course;
import com.sytoss.web.dto.save.CourseSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper extends BaseMapper<Course, CourseSaveDTO> {

    @Autowired
    public CourseMapper(ModelMapper mapper) {
        super(Course.class, CourseSaveDTO.class);
    }

    @Override
    public Course toEntity(CourseSaveDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public CourseSaveDTO toDTO(Course entity) {
        return super.toDTO(entity);
    }
}
