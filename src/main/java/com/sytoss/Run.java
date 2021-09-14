package com.sytoss;

import com.sytoss.config.Config;

import com.sytoss.service.CourseService;
import com.sytoss.service.impl.CourseServiceImpl;
import com.sytoss.web.dto.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;


public class Run {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);

//        MediaDTO photo = new MediaDTO(1L,"bvgdevui");
//        MediaDTO cert = new MediaDTO(2L,"rg");
//        MediaDTO lesson = new MediaDTO(3L,"lesson is cool");
//        CategoryDTO categoryDTO = new CategoryDTO(1L,"guibvgui");
//        PriceDTO priceDTO= new PriceDTO("Regular", new BigDecimal("250"));
//        LessonTemplateDTO lessonTemplateDTO= new LessonTemplateDTO("Hello","How low", lesson, 40);
//        TopicDTO topicDTO= new TopicDTO("brf", "Bear", Collections.singletonList(lessonTemplateDTO));
//        CourseDTO courseDTO= new CourseDTO("bt5gbymn0jyhicfvigiihgf2u11698cr11111o,lfki", "HBGOI", "bguig", categoryDTO, cert, photo, Collections.singletonList(topicDTO),Collections.singletonList(priceDTO));
//
//        CourseService courseService = appContext.getBean(CourseService.class);
//
//        courseService.createCourse(courseDTO);
    }
}