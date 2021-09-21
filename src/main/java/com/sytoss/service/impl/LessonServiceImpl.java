package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchLessonException;
import com.sytoss.model.Lookup;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.user.Mentor;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.communication.CommunicationRepository;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.LessonTemplateRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.LessonService;
import com.sytoss.web.dto.filter.FilterCommunicationDTO;
import com.sytoss.web.dto.filter.FilterLessonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    private final LessonRepository lessonRepository;

    private final HomeworkRepository homeworkRepository;

    private final StudyGroupRepository studyGroupRepository;

    private final CommunicationRepository communicationRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final UserAccountRepository userAccountRepository;

    private final LookupRepository lookupRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, HomeworkRepository homeworkRepository,
                             StudyGroupRepository studyGroupRepository, CommunicationRepository communicationRepository,
                             LessonTemplateRepository lessonTemplateRepository, UserAccountRepository userAccountRepository,
                             LookupRepository lookupRepository) {
        this.lessonRepository = lessonRepository;
        this.homeworkRepository = homeworkRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.communicationRepository = communicationRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.userAccountRepository = userAccountRepository;
        this.lookupRepository = lookupRepository;
    }

    @Override
    public boolean createLesson(Lesson lesson) {
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
        if (lessonRepository.findById(lesson.getId()) == null)
            throw new NoSuchLessonException();

        for (Comment comment : lesson.getComments()) {
            comment.setActive(false);
            communicationRepository.save(comment);
        }
        return true;
    }

    @Override
    public List<Lesson> findLessonsByFilter(FilterLessonDTO filter) {
        switch (filter.getFilter()) {
            case TIME_PERIOD: {
                StudyGroup studyGroup = studyGroupRepository.findOne(filter.getStudyGroupId());
                return lessonRepository.findLessonsByTimePeriodAndStudyGroup(studyGroup, filter.getStartTimePeriod(),
                        filter.getEndDatePeriod());
            }
            case FUTURE_LESSONS_FOR_STUDY_GROUP: {
                StudyGroup studyGroup = studyGroupRepository.findOne(filter.getStudyGroupId());
                return lessonRepository.findFutureLessonsByStudyGroup(studyGroup);
            }
            case LESSON_TEMPLATE: {
                LessonTemplate lessonTemplate = lessonTemplateRepository.findOne(filter.getLessonTemplateId());
                return lessonRepository.findLessonByLessonTemplate(lessonTemplate);
            }
            case MENTOR: {
                try {
                    Mentor mentor = (Mentor) userAccountRepository.findOne(filter.getMentorId());
                    return lessonRepository.findLessonByMentor(mentor);
                } catch (ClassCastException exception) {
                    logger.error("Incorrect mentor Id was provided, id - {}", filter.getMentorId());
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public List<Comment> findCommentsByFilter(FilterCommunicationDTO filter) {
        List<Comment> comments = new ArrayList<>();
        List<Communication> communications;
        switch (filter.getFilter()) {
            case OLDEST:
                communications = communicationRepository.findByOrderBySendDate();
                break;

            case NEWEST:
                communications = communicationRepository.findByOrderBySendDateDesc();
                break;
            default:
                return null;
        }

        for (Communication communication : communications) {
            comments.add((Comment) communication);
        }

        return comments;
    }

    private boolean saveLesson(Lesson lesson) {
        if (lesson == null)
            throw new NullPointerException();

        Lesson saveLesson = lessonRepository.save(lesson);
        return true;
    }
}
