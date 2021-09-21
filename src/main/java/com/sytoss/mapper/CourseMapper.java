package com.sytoss.mapper;

import com.sytoss.model.course.Course;
import com.sytoss.web.dto.CourseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper extends BaseMapper<Course, CourseDTO> {

    @Autowired
    public CourseMapper() {
        super(Course.class, CourseDTO.class);
    }

    @Override
    public Course toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public CourseDTO toDTO(Course entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Course> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<CourseDTO> toListDTO(List<Course> courses) {
        return super.toListDTO(courses);
    }
}
