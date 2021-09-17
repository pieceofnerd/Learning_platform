package com.sytoss.service;

import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.course.Course;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import com.sytoss.web.dto.save.CourseSaveDTO;
import com.sytoss.web.dto.FilterDTO;


import java.util.List;

public interface CourseService {

    boolean createCourse(Course course) throws NoSuchCourseException;

    boolean updateCourse(Course course) throws NoSuchCourseException;

    boolean closeCourse(Course course) throws NoSuchCourseException;

    List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException;

    List<Course> getAll();

}
