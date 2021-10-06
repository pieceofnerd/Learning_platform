package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.CommunicationNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchCommunicationException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;

import java.util.List;

public interface CommunicationService {
    void createCommunication(Communication communication) throws CommunicationNoContentException;

    void updateCommunication(Communication feedback) throws NoSuchCommunicationException, CommunicationNoContentException;

    void deleteCommunication(Communication communication) throws NoSuchCommunicationException, CommunicationNoContentException;

    List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask);
}
