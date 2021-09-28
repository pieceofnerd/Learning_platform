package com.sytoss.controller;

import com.sytoss.exception.*;
import com.sytoss.mapper.CourseMapper;
import com.sytoss.mapper.LessonTemplateMapper;
import com.sytoss.mapper.TopicMapper;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.LessonTemplateDTO;
import com.sytoss.web.dto.TopicDTO;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import com.sytoss.web.dto.save.CourseSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final TopicMapper topicMapper;
    private final LessonTemplateMapper lessonTemplateMapper;

    public void createCourse(CourseSaveDTO courseSaveDTO) {
        final Course course = courseMapper.toEntity(courseSaveDTO);
        try {
            courseService.createCourse(course);
        } catch (NoSuchCourseException | DuplicateCourseNameException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(CourseDTO courseDTO) {
        final Course course = courseMapper.toEntity(courseDTO);
        try {
            courseService.updateCourse(course);
        } catch (NoSuchCourseException e) {
            e.printStackTrace();
        }
    }

    public void closeCourse(CourseDTO courseDTO) {
        final Course course = courseMapper.toEntity(courseDTO);
        try {
            courseService.closeCourse(course);
        } catch (NoSuchCourseException e) {
            e.printStackTrace();
        }
    }

    public void removeTopic(TopicDTO topicDTO) throws NoSuchTopicException {
        final Topic topic = topicMapper.toEntity(topicDTO);
        courseService.removeTopic(topic);
    }

    public void removeLessonTemplate(LessonTemplateDTO lessonTemplateDTO) {
        final LessonTemplate lessonTemplate = lessonTemplateMapper.toEntity(lessonTemplateDTO);
        try {
            courseService.removeLessonTemplate(lessonTemplate);
        } catch (NoSuchLessonTemplateException e) {
            e.printStackTrace();
        }
    }

    public List<CourseDTO> getAll() {
        return courseMapper.toListDTO(courseService.getAll());
    }

    public List<CourseDTO> findByFilter(FilterCourseDTO filter) {
        List<CourseDTO> courses = new ArrayList<>();
        try {
            courses.addAll(courseMapper.toListDTO(courseService.findByFilter(filter)));
        } catch (NoSuchUserAccountException e) {
            e.printStackTrace();
        }
        return courses;
    }
}