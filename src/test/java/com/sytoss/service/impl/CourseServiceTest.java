package com.sytoss.service.impl;

import com.sytoss.config.Config;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.course.Course;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;

class CourseServiceTest {

    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    private final CourseService courseService = context.getBean(CourseService.class);
    private final CourseRepository courseRepository = context.getBean(CourseRepository.class);



    @org.junit.jupiter.api.Test
    void closeCourse() throws NoSuchCourseException {
        courseService.closeCourse(courseRepository.findOne(2L));
        Assert.isTrue(!courseRepository.findOne(2L).getActive());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        courseService.getAll();
    }

    @Test
    void findByFilter() throws NoSuchUserAccountException {
        FilterCourseDTO courseDTO = new FilterCourseDTO(Filter.COST_RANGE, new BigDecimal("40.0"), new BigDecimal("240.0"), 1L);
        List<Course> courseList = courseService.findByFilter(courseDTO);
        for (Course course : courseList) {
            System.out.println(course);
        }
    }

    @Test
    void findById() {
        courseService.findById(80L);
    }
}