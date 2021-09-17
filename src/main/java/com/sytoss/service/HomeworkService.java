package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.filter.FilterHomeworkDTO;

import java.util.List;

public interface HomeworkService {

    boolean createHomework(Homework homework);

    boolean updateHomework(Homework homework);

    boolean deleteHomework(Homework homework);

    List<Homework> findHomeworkFindByFilter(FilterHomeworkDTO filter) throws Exception;

    boolean leaveFeedback(Communication feedback);

}
