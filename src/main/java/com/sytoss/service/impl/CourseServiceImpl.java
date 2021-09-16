package com.sytoss.service.impl;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.model.course.Course;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.FilterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean createCourse(Course course) throws NoSuchCourseException {
        boolean created = saveCourse(course);
        if (created)
            logger.info("Course {} was created", course.getName());
        return created;
    }

    @Override
    public boolean updateCourse(Course course) throws NoSuchCourseException {
        boolean updated = saveCourse(course);
        if (updated)
            logger.info("Course {} was updated", course.getName());
        return updated;
    }

    private boolean saveCourse(Course course) throws NoSuchCourseException {
        if (course == null) {
            throw new NoSuchCourseException();
        }
        try {
            validateCourseName(course);
            courseRepository.save(course);
            return true;
        } catch (DuplicateCourseNameException e) {
            logger.error("Course with name {}  already exists on Platform", course.getName());
            return false;
        }
    }

    @Override
    public boolean closeCourse(Course course) {
        course.setActive(false);
        return true;
    }

    @Override
    public Course findByFilter(FilterDTO filter) {
        //TODO
        return null;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    private void validateCourseName(Course course) throws DuplicateCourseNameException {
        List<Course> courses = courseRepository.findCoursesByActiveIsTrue();
        for (Course currentCourse : courses) {
            if (currentCourse.getName().equals(course.getName())) {
                throw new DuplicateCourseNameException();
            }
        }
    }


}
