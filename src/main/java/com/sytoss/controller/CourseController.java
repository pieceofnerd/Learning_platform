package com.sytoss.controller;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.*;
import com.sytoss.mapper.course.CourseMapper;
import com.sytoss.mapper.course.LessonTemplateMapper;
import com.sytoss.mapper.course.TopicMapper;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.LessonTemplateDTO;
import com.sytoss.web.dto.TopicDTO;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import com.sytoss.web.dto.save.CourseSaveDTO;
import com.sytoss.web.dto.update.CourseUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final TopicMapper topicMapper;
    private final LessonTemplateMapper lessonTemplateMapper;

    public void createCourse(CourseSaveDTO courseSaveDTO) {
        final Course course = courseMapper.toEntity(courseSaveDTO);
        try {
            courseService.createCourse(course);
        } catch (NoSuchCourseException | DuplicateCourseNameException | CourseNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void updateCourse(CourseUpdateDTO courseDTO) {
        final Course course = courseMapper.toEntity(courseDTO);
        try {
            courseService.updateCourse(course);
        } catch (NoSuchCourseException e) {
            logger.error(e.getMessage());
        }
    }

    public void closeCourse(CourseDTO courseDTO) {
        final Course course = courseMapper.toEntity(courseDTO);
        try {
            courseService.closeCourse(course);
        } catch (NoSuchCourseException e) {
            logger.error(e.getMessage());
        }
    }

    public void removeTopic(TopicDTO topicDTO) throws NoSuchTopicException, TopicNoContentException {
        final Topic topic = topicMapper.toEntity(topicDTO);
        try {
            courseService.removeTopic(topic);
        } catch (TopicNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void removeLessonTemplate(LessonTemplateDTO lessonTemplateDTO) {
        final LessonTemplate lessonTemplate = lessonTemplateMapper.toEntity(lessonTemplateDTO);
        try {
            courseService.removeLessonTemplate(lessonTemplate);
        } catch (NoSuchLessonTemplateException | LessonTemplateNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public List<CourseDTO> getAll() {
        List<Course> courses = courseService.getAll();
        return courseMapper.toListDTO(courseService.getAll());
    }

    public List<CourseDTO> findByFilter(FilterCourseDTO filter) {
        List<CourseDTO> courses = new ArrayList<>();
        try {
            courses.addAll(courseMapper.toListDTO(courseService.findByFilter(filter)));
        } catch (NoSuchUserAccountException | NoSuchCategoryException | CategoryNoContentException | UserAccountNoContentException e) {
            logger.error(e.getMessage());
        }
        return courses;
    }
}