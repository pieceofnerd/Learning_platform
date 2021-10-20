package com.sytoss.controller;

import com.sytoss.exception.no_contet_exception.LessonNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchLessonException;
import com.sytoss.mapper.communication.CommunicationMapper;
import com.sytoss.mapper.course.HomeTaskMapper;
import com.sytoss.mapper.course.LessonMapper;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.Lesson;
import com.sytoss.service.LessonService;
import com.sytoss.web.dto.CommunicationDTO;
import com.sytoss.web.dto.LessonDTO;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;
import com.sytoss.web.dto.save.LessonSaveDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonController {
    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);
    private final LessonService lessonService;
    private final LessonMapper lessonMapper;
    private final CommunicationMapper communicationMapper;
    private final HomeTaskMapper homeTaskMapper;

    public LessonDTO createLesson(LessonSaveDTO lessonSaveDTO) {
        try {
            Lesson lesson = lessonMapper.toEntity(lessonSaveDTO);
            if (lesson != null) {
                lesson = lessonService.createLesson(lesson);
                return lessonMapper.toDTO(lesson);
            }
        } catch (RuntimeException | LessonNoContentException e) {
            System.out.println("Something went wrong. Please, try again");
        }
        return null;
    }


    public void updateLesson(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.updateLesson(lesson);
        } catch (NoSuchLessonException | LessonNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteLesson(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.deleteLesson(lesson);
        } catch (NoSuchLessonException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteAllComments(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.deleteAllComments(lesson);
        } catch (NoSuchLessonException e) {
            logger.error(e.getMessage());
        }
    }

    public List<LessonDTO> findLessonsByFilter(FilterLessonDTO filter) {
        List<LessonDTO> lessons = lessonMapper.toListDTO(lessonService.findLessonsByFilter(filter));
        return lessons;
    }

    public List<CommunicationDTO> findCommentsByFilter(FilterCommunicationDTO filter) {
        List<Communication> comments = new ArrayList<Communication>(lessonService.findCommentsByFilter(filter));
        return communicationMapper.toListDTO(comments);
    }
}