package com.sytoss.service.impl;

import com.sytoss.mapper.CourseMapper;
import com.sytoss.mapper.LessonTemplateMapper;
import com.sytoss.mapper.PriceMapper;
import com.sytoss.mapper.TopicMapper;
import com.sytoss.model.PriceType;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.Price;
import com.sytoss.model.course.Topic;
import com.sytoss.repository.*;
import com.sytoss.service.CourseService;
import com.sytoss.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;

    private final TopicRepository topicRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final PriceRepository priceRepository;

    private final LookupRepository lookupRepository;

    private final TopicMapper topicMapper;

    private final CourseMapper courseMapper;

    private final LessonTemplateMapper lessonTemplateMapper;

    private final PriceMapper priceMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TopicRepository topicRepository, LessonTemplateRepository lessonTemplateRepository, PriceRepository priceRepository, LookupRepository lookupRepository, TopicMapper topicMapper, CourseMapper courseMapper, LessonTemplateMapper lessonTemplateMapper, PriceMapper priceMapper) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.priceRepository = priceRepository;
        this.lookupRepository = lookupRepository;
        this.topicMapper = topicMapper;
        this.courseMapper = courseMapper;
        this.lessonTemplateMapper = lessonTemplateMapper;
        this.priceMapper = priceMapper;
    }

    @Override
    public boolean createCourse(CourseDTO courseDTO) {
        if (!validate(courseDTO)) return false;

        Course course = courseRepository.save(courseMapper.toEntity(courseDTO));

        for (TopicDTO currentTopic : courseDTO.getTopics()) {
            Topic topic = addTopic(course, currentTopic);
            for (LessonTemplateDTO currentLesson : currentTopic.getLessonTemplates()) {
                addLessonTemplate(topic, currentLesson);
            }
        }

        for (PriceDTO currentPrice : courseDTO.getPrices()) {
            addPrice(course, currentPrice);
        }

        return true;
    }


    @Override
    public boolean updateCourse(Course course) {
        return false;
    }

    @Override
    public boolean closeCourse(Course course) {
        return false;
    }

    @Override
    public Course findByFilter(FilterDTO filter) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    private boolean validate(CourseDTO courseDTO) {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            if (course.getName().equals(courseDTO.getName())) {
                //TODO
                return false;
            }
        }
        return true;
    }

    private Topic addTopic(Course course, TopicDTO currentTopic) {

        Topic topic = topicMapper.toEntity(currentTopic);
        topic.setCourse(course);
        topicRepository.save(topic);
        return topic;
    }

    private void addLessonTemplate(Topic topic, LessonTemplateDTO currentLesson) {
        LessonTemplate lesson = lessonTemplateMapper.toEntity(currentLesson);
        lesson.setTopic(topic);
        lessonTemplateRepository.save(lesson);
    }

    private void addPrice(Course course, PriceDTO currentPrice) {
        Price price = priceMapper.toEntity(currentPrice);
        for (PriceType value : PriceType.values()) {
            if (value.name().equals(currentPrice.getPriceType().toUpperCase())) {
                Long lookupId = value.getValue();
                price.setPriceType(lookupRepository.findOne(lookupId));
                break;
            }
        }
        if(price.getPriceType()==null){
            //TODO
        }
        price.setCourse(course);
        priceRepository.save(price);
    }

}
