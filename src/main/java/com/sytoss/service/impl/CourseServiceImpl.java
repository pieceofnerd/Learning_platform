package com.sytoss.service.impl;

import com.sytoss.exception.DuplicateCourseNameException;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.exception.NoSuchTopicException;
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
    public CourseServiceImpl(CourseRepository courseRepository, TopicRepository topicRepository,
                             LessonTemplateRepository lessonTemplateRepository, LookupRepository lookupRepository,
                             UserAccountRepository userAccountRepository) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lookupRepository = lookupRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createCourse(Course course) {
        try {
            validateCourseName(course);
            saveCourse(course);
            logger.info("Course {} was created", course.getName());
        } catch (DuplicateCourseNameException e) {
            logger.error("Course with name {}  already exists on Platform", course.getName());
        }
    }

    @Override
    public void updateCourse(Course course) throws NoSuchCourseException {
        checkCourseExistence(course);
        courseRepository.save(course);
        logger.info("Course {} was updated", course.getName());
    }


    @Override
    public void closeCourse(Course course) throws NoSuchCourseException {
        checkCourseExistence(course);
        course.setActive(false);
        courseRepository.save(course);
        logger.info("Course {} was disabled", course.getName());
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
    public void removeTopic(Topic topic) throws NullPointerException {
        if (topic == null) {
            logger.error("Topic could not be null");
            throw new NullPointerException();
        }
        topic.setActive(false);
        topicRepository.save(topic);
        logger.info("Topic {} was removed from {} course", topic.getName(), topic.getCourse().getName());
    }

    @Override
    public void removeLessonTemplate(LessonTemplate lessonTemplate) throws NullPointerException {
        if (lessonTemplate == null) {
            logger.error("Topic could not be null");
            throw new NullPointerException();
        }
        lessonTemplate.setActive(false);
        lessonTemplateRepository.save(lessonTemplate);
        logger.info("Lesson template {} was removed from {} topic", lessonTemplate.getName(), lessonTemplate.getTopic().getName());
    }

    @Override
    public void addTopic(Topic topic) throws NoSuchCourseException, NullPointerException {
        checkCourseExistence(topic.getCourse());

        if (topic == null) {
            logger.error("Topic must not be null");
            throw new NullPointerException();
        }
        topicRepository.save(topic);
        logger.info("Topic {} was added to {} course", topic.getName(), topic.getCourse().getName());
    }

    @Override
    public void addLessonTemplate(LessonTemplate lessonTemplate) throws NullPointerException, NoSuchTopicException {

        Topic topic = topicRepository.findOne(lessonTemplate.getTopic().getId());

        if (topic == null){
            logger.error("Couldn't find topic with id: {}", lessonTemplate.getTopic().getId());
            throw new NoSuchTopicException();
        }

            if (lessonTemplate == null) {
                logger.error("Lesson template must not be null");
                throw new NullPointerException();
            }
        lessonTemplateRepository.save(lessonTemplate);
        logger.info("Lesson template {} was added to {} topic", lessonTemplate.getName(), lessonTemplate.getTopic().getName());
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
        String courseNameWithoutDoubleSpaces = course.getName().replace(" +", " ").trim();
        if (courseRepository.findCourseByName(courseNameWithoutDoubleSpaces) != null)
            throw new DuplicateCourseNameException();
    }

    private List<Course> findCoursesByPriceRange(FilterCourseDTO filter) throws NoSuchUserAccountException {
        UserAccount student = userAccountRepository.findOne(filter.getStudentId());

        if (student == null) {
            logger.error("Couldn't find students account with id: {}", filter.getStudentId());
            throw new NoSuchUserAccountException();
        }

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

    private void saveCourse(Course course) throws NullPointerException {
        if (course == null) {
            logger.error("Course must not be null");
            throw new NullPointerException();
        }
        courseRepository.save(course);
    }

    private void checkCourseExistence(Course course) throws NoSuchCourseException {
        if (courseRepository.findOne(course.getId()) == null) {
            logger.error("Course {} wasn't be found ", course.getId());
            throw new NoSuchCourseException();
        }
    }

}
