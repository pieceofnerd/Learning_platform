package com.sytoss.service.impl;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.Lookup;
import com.sytoss.model.PriceType;
import com.sytoss.model.StudentStatus;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.LessonTemplateRepository;
import com.sytoss.repository.course.TopicRepository;
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

    private final TopicRepository topicRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final LookupRepository lookupRepository;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TopicRepository topicRepository, LessonTemplateRepository lessonTemplateRepository, LookupRepository lookupRepository, UserAccountRepository userAccountRepository) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lookupRepository = lookupRepository;
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

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAll() {
        List<Course> courses = courseRepository.findCoursesByActiveIsTrue();
        for (Course course : courses) {
            course.setTopics(course.getActiveTopics());
        }
        return courses;
    }

    @Override
    public boolean removeTopic(Topic topic) {
        if (topic == null) {
            return false;
        }
        topic.setActive(false);
        topicRepository.save(topic);
        return true;
    }

    @Override
    public boolean removeLessonTemplate(LessonTemplate lessonTemplate) {
        if (lessonTemplate == null) {
            return false;
        }
        lessonTemplate.setActive(false);
        lessonTemplateRepository.save(lessonTemplate);
        return true;
    }

    @Override
    public List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchUserAccountException {
        switch (filter.getFilter()) {
            case NEWEST:
                return courseRepository.findDistinctFirst10ByOrderByCreatedDateDesc();
            case COST_RANGE: {
                return findCoursesByPriceRange(filter);
            }
        }
        return null;
    }

    private void validateCourseName(Course course) throws DuplicateCourseNameException {
      String courseNameWithoutDoubleSpaces = course.getName().replace(" +"," ").trim();
      if(courseRepository.findCourseByName(courseNameWithoutDoubleSpaces)!=null)
          throw new DuplicateCourseNameException();
    }

    private List<Course> findCoursesByPriceRange(FilterCourseDTO filter) {
        UserAccount student = userAccountRepository.findOne(filter.getStudentId());

        if (student == null)
            return null;

        if (!student.getClass().isAssignableFrom(Student.class)) {
            logger.error("Course Filter contains incorrect student id");
            throw new ClassCastException("Incorrect type for Student.class");
        }
        Lookup price;
        if (Objects.equals(((Student) student).getStudentStatus().getId(), StudentStatus.NEWBIE.getValue()))
            price = lookupRepository.findOne(PriceType.REGULAR.getValue());
        else
            price = lookupRepository.findOne(PriceType.PREMIUM.getValue());

        return courseRepository.findCourseByPriceRange(price, filter.getLowCost(), filter.getHighCost());

    }

}
