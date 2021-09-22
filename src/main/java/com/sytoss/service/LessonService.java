package com.sytoss.service;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;

import java.util.List;

public interface LessonService {

    void createLesson(Lesson lesson) throws NoSuchLessonException;

    void updateLesson(Lesson lesson) throws NoSuchLessonException;

    void deleteLesson(Lesson lesson) throws NoSuchLessonException;

    void deleteAllComments(Lesson lesson) throws NoSuchLessonException;

    List<Lesson> findLessonsByFilter(FilterLessonDTO filter);

    List<Comment> findCommentsByFilter(FilterCommunicationDTO filter);

}
