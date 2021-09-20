package com.sytoss.service;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface LessonService {

    boolean createLesson(Lesson lesson) throws NoSuchLessonException;

    boolean updateLesson(Lesson lesson) throws NoSuchLessonException;

    boolean deleteLesson(Lesson lesson) throws NoSuchLessonException;

    boolean deleteAllComments(Lesson lesson) throws NoSuchLessonException;

    List<Lesson> findLessonsByFilter(FilterDTO filterDTO);

    List<Comment> findCommentsByFilter(FilterDTO filterDTO);

}
