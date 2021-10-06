package com.sytoss.service;

import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.web.dto.filter.FilterCourseDTO;

import java.util.List;

public interface CourseService {

    void createCourse(Course course) throws NoSuchCourseException, DuplicateCourseNameException, CourseNoContentException;

    void updateCourse(Course course) throws NoSuchCourseException;

    void closeCourse(Course course) throws NoSuchCourseException;

    void removeTopic(Topic topic) throws NoSuchTopicException, TopicNoContentException;

    void removeLessonTemplate(LessonTemplate lessonTemplate) throws NoSuchLessonTemplateException, LessonTemplateNoContentException;

    void updateCourseRating(Course course) throws NoSuchCourseException, CourseNoContentException;

    List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException, NoSuchCategoryException, CategoryNoContentException, UserAccountNoContentException;

    List<Course> getAll();

    Course findById(Long id) throws NoSuchCourseException;

}
