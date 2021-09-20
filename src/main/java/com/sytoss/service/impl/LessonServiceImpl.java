package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.education.Homework;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.service.LessonService;
import com.sytoss.web.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final HomeworkRepository homeworkRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, HomeworkRepository homeworkRepository) {
        this.lessonRepository = lessonRepository;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public boolean createLesson(Lesson lesson) throws NoSuchLessonException {
        return saveLesson(lesson);
    }

    @Override
    public boolean updateLesson(Lesson lesson) throws NoSuchLessonException {
        if (lessonRepository.findOne(lesson.getId()) == null)
            throw new NoSuchLessonException();
        return saveLesson(lesson);
    }

    @Override
    public boolean deleteLesson(Lesson lesson) throws NoSuchLessonException {
        if (lessonRepository.findOne(lesson.getId()) == null)
            throw new NoSuchLessonException();
        lesson.setActive(false);
        return saveLesson(lesson);
    }


    @Override
    @Transactional
    public boolean deleteAllComments(Lesson lesson) throws NoSuchLessonException {
        if (lessonRepository.findOne(lesson.getId()) == null)
            throw new NoSuchLessonException();

        for (Comment comment : lesson.getComments()) {
            comment.setActive(false);
        }

       return saveLesson(lesson);
    }

    @Override
    public List<Lesson> findLessonsByFilter(FilterDTO filterDTO) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByFilter(FilterDTO filterDTO) {
        return null;
    }

    private boolean saveLesson(Lesson lesson) throws NoSuchLessonException {
        if (lesson == null)
            throw new NullPointerException();

        Lesson saveLesson = lessonRepository.save(lesson);
        return true;
    }
}
