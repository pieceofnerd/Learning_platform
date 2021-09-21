package com.sytoss.service.impl;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.communication.CommunicationRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.service.HomeworkService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.filter.FilterHomeworkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final CommunicationRepository communicationRepository;
    private final UserAccountService userAccountService;

    @Override
    public boolean createHomework(Homework homework) {
        if (homework == null)
            return false;

        homeworkRepository.save(homework);
        return true;
    }

    @Override
    public boolean updateHomework(Homework homework) {
        return false;
    }

    @Override
    public boolean deleteHomework(Homework homework) {
        if (homework == null)
            return false;

        if (!homeworkRepository.exists(homework.getId()))
            return false;

        homework.setActive(false);
        homeworkRepository.save(homework);
        return true;
    }


    @Override
    public List<Homework> findHomeworkFindByFilter(FilterHomeworkDTO filter) throws Exception {

        List<Homework> homeworks = new ArrayList<>();
        switch (filter.getFilter()) {
            case AUTHOR: {
                homeworks.addAll(homeworkRepository.findAllByAuthorAndActiveIsTrue(userAccountService.findUserAccountById(filter.getAuthor())));
                break;
            }
        }
        return homeworks;
    }

    @Override
    public boolean leaveFeedback(Communication feedback) {
        if (feedback == null) {
            return false;
        }
        communicationRepository.save(feedback);
        return true;
    }
}