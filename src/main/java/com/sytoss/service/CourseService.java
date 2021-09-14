package com.sytoss.service;

import com.sytoss.model.course.Course;
import com.sytoss.web.dto.save.CourseSaveDTO;
import com.sytoss.web.dto.save.FilterSaveDTO;

import java.util.List;

public interface CourseService {

    boolean createCourse(CourseSaveDTO course);

    boolean updateCourse(CourseSaveDTO courseDTO);

    boolean closeCourse(Course course);

    Course findByFilter(FilterSaveDTO filter);

    List<Course> getAll();

}
