package com.sytoss.service;

import com.sytoss.exception.NoSuchHomeworkException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Homework;
import com.sytoss.web.dto.filter.FilterHomeworkDTO;

import java.util.List;

public interface HomeworkService {

    void createHomework(Homework homework);

    void updateHomework(Homework homework) throws NoSuchHomeworkException;

    void deleteHomework(Homework homework) throws NoSuchHomeworkException;

    List<Homework> findHomeworkFindByFilter(FilterHomeworkDTO filter) throws Exception;

    void leaveFeedback(Communication feedback) throws NoSuchHomeworkException;

}
