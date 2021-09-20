package com.sytoss.service.impl;

import com.sytoss.config.Config;
import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.course.Lesson;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.service.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceTest {
    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    private final LessonService lessonService = context.getBean(LessonService.class);
    private final LessonRepository lessonRepository = context.getBean(LessonRepository.class);

    @Test
    void deleteAllComments() throws NoSuchLessonException {
        Lesson lesson = lessonRepository.findOne(5L);
        lessonService.deleteAllComments(lesson);
    }
}