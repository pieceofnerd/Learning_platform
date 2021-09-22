package com.sytoss.service;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchTopicException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import com.sytoss.web.dto.save.CourseSaveDTO;
import com.sytoss.web.dto.FilterDTO;


import java.util.List;

public interface CourseService {

    void createCourse(Course course) throws NoSuchCourseException, DuplicateCourseNameException;

    void updateCourse(Course course) throws NoSuchCourseException;

    void closeCourse(Course course) throws NoSuchCourseException;

    void removeTopic(Topic topic) throws NoSuchTopicException;

    void removeLessonTemplate(LessonTemplate lessonTemplate);

    void addTopic( Topic topic) throws NoSuchCourseException;

    void addLessonTemplate( LessonTemplate lessonTemplate) throws NoSuchTopicException;

    List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException;

    List<Course> getAll();

}
