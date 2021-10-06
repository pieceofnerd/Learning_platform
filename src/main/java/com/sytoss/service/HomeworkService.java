package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.FeedbackNoContentException;
import com.sytoss.exception.no_contet_exception.HomeworkNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchHomeworkException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Homework;
import com.sytoss.web.dto.filter.FilterHomeworkDTO;

import java.util.List;

public interface HomeworkService {

    void createHomework(Homework homework) throws HomeworkNoContentException;

    void updateHomework(Homework homework) throws NoSuchHomeworkException;

    void deleteHomework(Homework homework) throws NoSuchHomeworkException, HomeworkNoContentException;

    List<Homework> findHomeworkFindByFilter(FilterHomeworkDTO filter) ;

    void leaveFeedback(Communication feedback) throws NoSuchHomeworkException, FeedbackNoContentException;

}
