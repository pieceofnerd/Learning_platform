package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.LessonNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchLessonException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;

import java.util.List;

public interface LessonService {

    Lesson createLesson(Lesson lesson) throws LessonNoContentException;

    void updateLesson(Lesson lesson) throws NoSuchLessonException, LessonNoContentException;

    void deleteLesson(Lesson lesson) throws NoSuchLessonException;

    void deleteAllComments(Lesson lesson) throws NoSuchLessonException;

    List<Lesson> findLessonsByFilter(FilterLessonDTO filter);

    List<Comment> findCommentsByFilter(FilterCommunicationDTO filter);

}
