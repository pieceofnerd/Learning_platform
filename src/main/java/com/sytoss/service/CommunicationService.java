package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;

import java.util.List;

public interface CommunicationService {
    boolean createComment(Communication communication);

    boolean createFeedback(Communication feedback);

    boolean updateFeedback(Communication feedback);

    boolean delete(Communication communication);

    List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask);
}
