package com.sytoss.service.impl;

import com.sytoss.exception.no_such_exception.NoSuchLessonException;

import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.LessonTemplate;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.user.Mentor;
import com.sytoss.model.enums.HomeworkStatus;
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

    private final StudyGroupRepository studyGroupRepository;

    private final HomeworkRepository homeworkRepository;

    private final CommunicationRepository communicationRepository;

    private final LessonTemplateRepository lessonTemplateRepository;

    private final LookupRepository lookupRepository;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, StudyGroupRepository studyGroupRepository,
                             HomeworkRepository homeworkRepository, CommunicationRepository communicationRepository,
                             LessonTemplateRepository lessonTemplateRepository, LookupRepository lookupRepository,
                             UserAccountRepository userAccountRepository) {
        this.lessonRepository = lessonRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.homeworkRepository = homeworkRepository;
        this.communicationRepository = communicationRepository;
        this.lessonTemplateRepository = lessonTemplateRepository;
        this.lookupRepository = lookupRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        Lesson savedLesson = saveLesson(lesson);
        createHomeworks(lesson);
        logger.info("Lesson {} was created", lesson.toString());
        return savedLesson;
    }


    @Override
    public void updateLesson(Lesson lesson) throws NoSuchLessonException {
        checkExistence(lesson);
        //homeTaskRepository.save(lesson.getHomeTask());
        saveLesson(lesson);
        logger.info("Lesson {} was updated", lesson.getId());
    }

    private void checkExistence(Lesson lesson) throws NoSuchLessonException {
        if (!lessonRepository.exists(lesson.getId())) {
            logger.error("Couldn't find lesson with id: {}", lesson.getId());
            throw new NoSuchLessonException();
        }
    }

    @Override
    public void deleteLesson(Lesson lesson) throws NoSuchLessonException {
        checkExistence(lesson);
        lesson.setActive(false);
        lessonRepository.save(lesson);
        logger.info("Lesson {} was disabled", lesson.getId());
    }

    @Override
    public void deleteAllComments(Lesson lesson) throws NoSuchLessonException {
        checkExistence(lesson);

        for (Comment comment : lesson.getComments()) {
            comment.setActive(false);
            communicationRepository.save(comment);
        }
        logger.info("Comments were deleted from lesson {} ", lesson.getId());
    }

    @Override
    public List<Lesson> findLessonsByFilter(FilterLessonDTO filter) {
        switch (filter.getFilter()) {
            case TIME_PERIOD: {
                StudyGroup studyGroup = studyGroupRepository.findOne(filter.getStudyGroupId());
                List<Lesson> lessons= lessonRepository.findLessonsByTimePeriodAndStudyGroupAndActiveIsTrue(studyGroup, filter.getStartTimePeriod(), filter.getEndDatePeriod());
                return lessons;
            }
            case FUTURE_LESSONS_FOR_STUDY_GROUP: {
                StudyGroup studyGroup = studyGroupRepository.findOne(filter.getStudyGroupId());
                return lessonRepository.findFutureLessonsByStudyGroupAndActiveIsTrue(studyGroup);
            }
            case LESSON_TEMPLATE: {
                LessonTemplate lessonTemplate = lessonTemplateRepository.findOne(filter.getLessonTemplateId());
                return lessonRepository.findLessonByLessonTemplateAndActiveIsTrue(lessonTemplate);
            }
            case MENTOR: {
                try {
                    Mentor mentor = (Mentor) userAccountRepository.findOne(filter.getMentorId());
                    return lessonRepository.findLessonByMentorAndActiveIsTrue(mentor);
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
                communications = communicationRepository.findByLessonIdOrderBySendDate(filter.getLessonId());
                break;

            case NEWEST:
                communications = communicationRepository.findByLessonIdOrderBySendDateDesc(filter.getLessonId());
                break;
            default:
                return null;
        }

        for (Communication communication : communications) {
            comments.add((Comment) communication);
        }

        return comments;
    }

    private Lesson saveLesson(Lesson lesson) {
        if (lesson == null) {
            logger.error("Lesson must not be null");
            throw new NullPointerException();
        }
        return lessonRepository.save(lesson);
    }

    private void createHomeworks(Lesson lesson) {
        StudyGroup studyGroup = lesson.getStudyGroup();
        for (Study study : studyGroup.getStudies()) {
            Homework homework = new Homework();
            homework.setHomeworkState(lookupRepository.findOne(HomeworkStatus.CREATED.getValue()));
            homework.setAuthor(study.getStudent());
            homework.setHomeTask(lesson.getHomeTask());
            homeworkRepository.save(homework);
        }
    }
}
