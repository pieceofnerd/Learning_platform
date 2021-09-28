package com.sytoss.service;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;

import java.util.List;

public interface LessonService {

    Lesson createLesson(Lesson lesson);

    void updateLesson(Lesson lesson) throws NoSuchLessonException;

    void deleteLesson(Lesson lesson) throws NoSuchLessonException;

    void deleteAllComments(Lesson lesson) throws NoSuchLessonException;

    List<Lesson> findLessonsByFilter(FilterLessonDTO filter);

    List<Comment> findCommentsByFilter(FilterCommunicationDTO filter);

}
