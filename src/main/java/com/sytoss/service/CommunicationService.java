package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;

import java.util.List;

public interface CommunicationService {
    void createComment(Communication communication);

    void createFeedback(Communication feedback);

    void updateFeedback(Communication feedback);

    void delete(Communication communication);

    List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask);
}
