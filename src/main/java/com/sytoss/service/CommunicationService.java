package com.sytoss.service;

import com.sytoss.model.communication.Communication;

public interface CommunicationService {
    boolean createComment(Communication communication);
    boolean createFeedback(Communication feedback);
    boolean updateFeedback(Communication feedback);
    boolean delete(Communication communication);
}
