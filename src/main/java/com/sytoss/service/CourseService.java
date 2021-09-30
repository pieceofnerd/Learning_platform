package com.sytoss.service;

import com.sytoss.exception.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.web.dto.filter.FilterCourseDTO;

import java.util.List;

public interface CourseService {

    void createCourse(Course course) throws NoSuchCourseException, DuplicateCourseNameException;

    void updateCourse(Course course) throws NoSuchCourseException;

    void closeCourse(Course course) throws NoSuchCourseException;

    void removeTopic(Topic topic) throws NoSuchTopicException;

    void removeLessonTemplate(LessonTemplate lessonTemplate) throws NoSuchLessonTemplateException;

    void updateCourseRating(Course course) throws NoSuchCourseException;

    List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException;

    List<Course> getAll();

}
