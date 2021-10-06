package com.sytoss.service.impl;

import com.sytoss.exception.no_such_exception.NoSuchHomeworkException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.education.Homework;
import com.sytoss.repository.communication.CommunicationRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.HomeworkService;
import com.sytoss.web.dto.filter.FilterHomeworkDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private static final Logger logger = LoggerFactory.getLogger(HomeworkServiceImpl.class);

    private final HomeworkRepository homeworkRepository;

    private final CommunicationRepository communicationRepository;

    private final UserAccountRepository userAccountRepository;

    @Override
    public void createHomework(Homework homework) {
        if (homework == null) {
            logger.error("Homework must not be null");
            return;
        }
        Homework savedHomework = homeworkRepository.save(homework);
        logger.info("Homework {} was created", savedHomework.toString());
    }

    @Override
    public void updateHomework(Homework homework) throws NoSuchHomeworkException {
        checkExistence(homework);
        homeworkRepository.save(homework);
        logger.info("Homework {} was updated", homework.getId());
    }

    private void checkExistence(Homework homework) throws NoSuchHomeworkException {
        if (!homeworkRepository.exists(homework.getId())) {
            logger.error("couldn't find homework with id: {}", homework.getId());
            throw new NoSuchHomeworkException();
        }
    }

    @Override
    public void deleteHomework(Homework homework) throws NoSuchHomeworkException {
        if (homework == null) {
            logger.error("Homework must not be null");
            return;
        }

        checkExistence(homework);
        homework.setActive(false);
        homeworkRepository.save(homework);
        logger.info("Homework {} was disabled", homework.getId());
    }

    @Override
    public List<Homework> findHomeworkFindByFilter(FilterHomeworkDTO filter)  {

        List<Homework> homeworks = new ArrayList<>();
        switch (filter.getFilter()) {
            case AUTHOR: {
                homeworks.addAll(homeworkRepository.findAllByAuthorAndActiveIsTrue(userAccountRepository.findOne(filter.getAuthor())));
                break;
            }
        }
        return homeworks;
    }

    @Override
    public void leaveFeedback(Communication feedback) throws NoSuchHomeworkException  {
        if (feedback == null) {
            logger.error("Feedback must not be null");
            return;
        }
        try {
            checkExistence(((Feedback) feedback).getHomework());
            communicationRepository.save(feedback);
            logger.info("Feedback {} was created", feedback);
        } catch (ClassCastException e) {
            logger.error(e.getMessage());
        }
    }
}