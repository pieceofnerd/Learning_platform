package com.sytoss.service;

import com.sytoss.model.course.Course;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface CourseService {

    boolean createCourse(CourseDTO course);

    boolean updateCourse(Course course);

    boolean closeCourse(Course course);

    Course findByFilter(FilterDTO filter);

    List<Course> getAll();

}
