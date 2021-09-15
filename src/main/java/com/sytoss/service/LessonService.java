package com.sytoss.service;

import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface LessonService {

    boolean createLesson();

    boolean updateLesson();

    boolean deleteLesson();

    List<Feedback> findFeedbacksByLesson();

    boolean deleteAllComments();

    List<Lesson> findLessonsByFilter(FilterDTO filterDTO);

    List<Comment> findCommentsByFilter(FilterDTO filterDTO);

}
