package com.sytoss.service;

import com.sytoss.model.education.Homework;

public interface HomeworkService {

    boolean createHomework(Homework homework);

    boolean updateHomework(Homework homework);

    boolean deleteHomework(Homework homework);

    //findHome

}
