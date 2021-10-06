package com.sytoss.service.impl;

import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.*;
import com.sytoss.model.Lookup;
import com.sytoss.model.course.*;
import com.sytoss.model.enums.PriceType;
import com.sytoss.model.enums.StudentStatus;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.*;
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

    private final CategoryRepository categoryRepository;

    private final TopicRepository topicRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final LookupRepository lookupRepository;

    private final UserAccountRepository userAccountRepository;

    private final CourseRatingRepository courseRatingRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CategoryRepository categoryRepository, TopicRepository topicRepository,
                             LessonTemplateRepository lessonTemplateRepository, LookupRepository lookupRepository,
                             UserAccountRepository userAccountRepository, CourseRatingRepository courseRatingRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lookupRepository = lookupRepository;
        this.userAccountRepository = userAccountRepository;
        this.courseRatingRepository = courseRatingRepository;
    }

    @Override
    public void createCourse(Course course) throws DuplicateCourseNameException, CourseNoContentException {
        if (course == null) {
            throw new CourseNoContentException("Course must not be null");
        }
        validateCourseName(course);
        saveCourse(course);
        logger.info("Course {} was created", course.getName());
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
    public void removeTopic(Topic topic) throws NoSuchTopicException, TopicNoContentException {
        if (topic == null) {
            throw new TopicNoContentException("Topic must not be null");
        }

        if (!topicRepository.exists(topic.getId())) {
            throw new NoSuchTopicException("Couldn't find topic with id: " + topic.getId());
        }
        topic.setActive(false);
        topicRepository.save(topic);
        logger.info("Topic {} was removed from {} course", topic.getName(), topic.getCourse().getName());
    }

    @Override
    public void removeLessonTemplate(LessonTemplate lessonTemplate) throws NoSuchLessonTemplateException, LessonTemplateNoContentException {
        if (lessonTemplate == null) {
            throw new LessonTemplateNoContentException("Lesson template must not be null");
        }

        if (!topicRepository.exists(lessonTemplate.getId())) {
            throw new NoSuchLessonTemplateException("Couldn't find topic with id: " + lessonTemplate.getId());
        }

        lessonTemplate.setActive(false);
        lessonTemplateRepository.save(lessonTemplate);
        logger.info("Lesson template {} was removed from {} topic", lessonTemplate.getName(), lessonTemplate.getTopic().getName());
    }

    @Override
    public List<Course> findByFilter(FilterCourseDTO filter) throws NoSuchCategoryException, NoSuchUserAccountException {
        switch (filter.getFilter()) {
            case NEWEST:
                return courseRepository.findDistinctFirst10ByActiveIsTrueOrderByCreatedDateDesc();
            case COST_RANGE: {
                Lookup price = definePriceType(filter);
                return courseRepository.findActiveCourseByPriceRange(price, filter.getLowCost(), filter.getHighCost());
            }
            case TOP_BY_RATING:
                return courseRepository.findDistinctFirst10ByActiveIsTrueOrderByRatingDesc();
            case LOW_HIGH: {
                Lookup price = definePriceType(filter);
                return courseRepository.findAllOrderByPriceDesc(price);
            }
            case HIGH_LOW: {
                Lookup price = definePriceType(filter);
                return courseRepository.findAllOrderByPriceAsc(price);
            }
            case CATEGORY: {
                Category category = categoryRepository.findOne(filter.getCategoryId());
                if (category == null) {
                    throw new NoSuchCategoryException("Couldn't find category with id: " + filter.getCategoryId());
                }
                return courseRepository.findActiveCoursesByCategory(category);
            }
        }
        return null;
    }

    @Transactional
    public Course findById(Long id) throws NoSuchCourseException {
        Course course = courseRepository.findById(id);
        checkCourseExistence(course);
        return course;
    }

    private void validateCourseName(Course course) throws DuplicateCourseNameException {
        String courseNameWithoutDoubleSpaces = course.getName().replace(" +", " ").trim();
        if (courseRepository.findCourseByNameAndActiveIsTrue(courseNameWithoutDoubleSpaces) != null)
            throw new DuplicateCourseNameException("Course with name " + course.getName() + "  already exists on Platform");
    }

    private Lookup definePriceType(FilterCourseDTO filter) throws NoSuchUserAccountException {
        UserAccount student = userAccountRepository.findOne(filter.getStudentId());

        if (student == null) {
            throw new NoSuchUserAccountException("Couldn't find students account with id: "+ filter.getStudentId());
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

        return price;
    }

    private void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourseRating(Course course) throws NoSuchCourseException {
        checkCourseExistence(course);
        course.setRating(courseRatingCalc(course));
        saveCourse(course);
    }

    private void checkCourseExistence(Course course) throws NoSuchCourseException {
        if (!courseRepository.exists(course.getId())) {
            logger.error("Course {} wasn't be found ", course.getId());
            throw new NoSuchCourseException("No such course exists");
        }
    }

    private Double courseRatingCalc(Course course) {
        List<CourseRating> courseRatings = courseRatingRepository.findCourseRatingByCourse(course);
        double rating = 0;
        for (CourseRating cr : courseRatings) {
            rating += cr.getRating();
        }
        return rating / courseRatings.size();
    }
}
