package com.sytoss.mapper;

import com.sytoss.model.course.Course;
import com.sytoss.web.dto.CourseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseMapper extends BaseMapper<Course, CourseDTO> {

    private final ModelMapper mapper;

    @Autowired
    public CourseMapper(ModelMapper mapper) {
        super(Course.class, CourseDTO.class, mapper);
        this.mapper = mapper;
    }

    @Override
    public Course toEntity(CourseDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public CourseDTO toDTO(Course entity) {
        return super.toDTO(entity);
    }
}
