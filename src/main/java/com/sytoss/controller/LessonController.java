package com.sytoss.controller;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.mapper.CommunicationMapper;
import com.sytoss.mapper.HomeTaskMapper;
import com.sytoss.mapper.LessonMapper;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.Lesson;
import com.sytoss.service.LessonService;
import com.sytoss.web.dto.CommunicationDTO;
import com.sytoss.web.dto.LessonDTO;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;
import com.sytoss.web.dto.save.HomeTaskSaveDTO;
import com.sytoss.web.dto.save.LessonSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonController {
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
        } catch (RuntimeException e) {
            System.out.println("Something went wrong. Please, try again");
        }
        return null;
    }


    public void updateLesson(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.updateLesson(lesson);
        } catch (NoSuchLessonException e) {
            e.printStackTrace();
        }
    }

    public void deleteLesson(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.deleteLesson(lesson);
        } catch (NoSuchLessonException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllComments(LessonDTO lessonDTO) {
        final Lesson lesson = lessonMapper.toEntity(lessonDTO);
        try {
            lessonService.deleteAllComments(lesson);
        } catch (NoSuchLessonException e) {
            e.printStackTrace();
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