package com.sytoss.service;

import com.sytoss.exception.NoSuchCommunicationException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;

import java.util.List;

public interface CommunicationService {
    void createCommunication(Communication communication);

    void updateCommunication(Communication feedback) throws NoSuchCommunicationException;

    void deleteCommunication(Communication communication) throws NoSuchCommunicationException;

    List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask);
}
