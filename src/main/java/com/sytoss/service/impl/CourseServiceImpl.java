package com.sytoss.service.impl;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.PriceType;
import com.sytoss.model.StudentStatus;
import com.sytoss.model.course.Course;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.filter.FilterCourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserAccountRepository userAccountRepository) {
        this.courseRepository = courseRepository;
        this.userAccountRepository = userAccountRepository;
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
    public boolean closeCourse(Course course) throws NoSuchCourseException {
        if (course == null)
            throw new NoSuchCourseException();

        course.setActive(false);
        courseRepository.save(course);
        return true;
    }

    @Override
    public List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException {
        switch (filter.getFilter()) {
            case NEWEST:
                return courseRepository.findDistinctFirst10ByOrderByCreatedDateDesc();
            case COST_RANGE: {
                UserAccount student = userAccountRepository.findOne(filter.getStudentId());

                if (student == null)
                    throw new NoSuchUserAccountException();

                if (!student.getClass().isAssignableFrom(Student.class)) {
                    logger.error("Course Filter contains incorrect student id");
                    throw new ClassCastException("Incorrect type for Student.class");
                }

                if (Objects.equals(((Student) student).getStudentStatus().getId(), StudentStatus.NEWBIE.getValue())) {
                    return courseRepository.findCourseByPriceRange(PriceType.REGULAR.getValue(),filter.getLowCost(), filter.getHighCost());
                }
                else return courseRepository.findCourseByPriceRange(PriceType.PREMIUM.getValue(),filter.getLowCost(), filter.getHighCost());

            }
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findCoursesByActiveIsTrue();
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
