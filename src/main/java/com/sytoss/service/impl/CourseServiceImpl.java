package com.sytoss.service.impl;

import com.sytoss.mapper.*;
import com.sytoss.model.Media;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Topic;
import com.sytoss.repository.*;
import com.sytoss.service.CourseService;
import com.sytoss.service.PriceService;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.save.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final PriceService priceService;

    private final CourseRepository courseRepository;

    private final TopicRepository topicRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final MediaRepository mediaRepository;

    private final CategoryRepository categoryRepository;

    private final TopicMapper topicMapper;

    private final CourseMapper courseMapper;

    private final LessonTemplateMapper lessonTemplateMapper;

    private final MediaMapper mediaMapper;

    @Autowired
    public CourseServiceImpl(PriceService priceService, CourseRepository courseRepository, TopicRepository topicRepository,
                             LessonTemplateRepository lessonTemplateRepository, MediaRepository mediaRepository,
                             CategoryRepository categoryRepository, TopicMapper topicMapper, CourseMapper courseMapper,
                             LessonTemplateMapper lessonTemplateMapper, MediaMapper mediaMapper) {
        this.priceService = priceService;
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.mediaRepository = mediaRepository;
        this.categoryRepository = categoryRepository;
        this.topicMapper = topicMapper;
        this.courseMapper = courseMapper;
        this.lessonTemplateMapper = lessonTemplateMapper;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public boolean createCourse(CourseSaveDTO courseDTO) {
        if (validateCourseName(courseDTO.getName())) return false;

        Media photo = mediaRepository.save(mediaMapper.toEntity(courseDTO.getCoursePhoto()));
        Media certificate = mediaRepository.save(mediaMapper.toEntity(courseDTO.getCertificateTemplate()));

        Course course = courseMapper.toEntity(courseDTO);
        course.setCoursePhoto(photo);
        course.setCertificateTemplate(certificate);
        course.setCategory(categoryRepository.findOne(courseDTO.getCategoryId()));
        course = courseRepository.save(course);

        for (TopicSaveDTO currentTopic : courseDTO.getTopics()) {
            Topic topic = addTopic(course, currentTopic);
            for (LessonTemplateSaveDTO currentLesson : currentTopic.getLessonTemplates()) {
                addLessonTemplate(topic, currentLesson);
            }
        }

        for (PriceSaveDTO currentPrice : courseDTO.getPrices()) {
            currentPrice.setCourseId(course.getId());
            priceService.createPrice(currentPrice);
        }

        return true;
    }

    @Override
    public boolean updateCourse(CourseSaveDTO courseDTO) {
        if (courseDTO == null) {
            return false;
        } else {
            if (validateCourseName(courseDTO.getName())) return false;
            //  courseRepository.save(course);
            //TODO
            return true;
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

    private boolean validateCourseName(String courseName) {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                //TODO
                return true;
            }
        }
        return false;
    }

    private Topic addTopic(Course course, TopicSaveDTO currentTopic) {

        Topic topic = topicMapper.toEntity(currentTopic);
        topic.setCourse(course);
        topicRepository.save(topic);
        return topic;
    }

    private void addLessonTemplate(Topic topic, LessonTemplateSaveDTO currentLesson) {
        LessonTemplate lesson = lessonTemplateMapper.toEntity(currentLesson);
        lesson.setTopic(topic);
        Media content = mediaRepository.save(mediaMapper.toEntity(currentLesson.getMedia()));
        lesson.setMedia(content);
        lessonTemplateRepository.save(lesson);
    }

//    private void addPrice(Course course, PriceSaveDTO currentPrice) {
//        Price price = priceMapper.toEntity(currentPrice);
//        if (price.getPriceType() == null) {
//            //TODO
//        }
//        price.setCourse(course);
//        price.setPriceType(lookupRepository.findOne(currentPrice.getPriceTypeId()));
//        priceRepository.save(price);
//    }

}
