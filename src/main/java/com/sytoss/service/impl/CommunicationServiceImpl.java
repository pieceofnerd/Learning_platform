package com.sytoss.service.impl;

import com.sytoss.exception.NoSuchCommunicationException;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;
import com.sytoss.repository.communication.CommunicationRepository;
import com.sytoss.service.CommunicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CommunicationServiceImpl implements CommunicationService {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

    private final CommunicationRepository communicationRepository;

    public CommunicationServiceImpl(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    @Override
    public void createCommunication(Communication communication) {
        if (communication == null) {
            logger.error("Communication must be not null");
            return;
        }
        communication = communicationRepository.save(communication);
        logger.info("Communication {} was created ", communication.toString());
    }


    @Override
    public void updateCommunication(Communication communication) throws NoSuchCommunicationException {
        if (communication == null) {
            logger.error("Communication must not be null");
            return;
        }
        checkCommunicationExistence(communication);
        communicationRepository.save(communication);
        logger.info("Communication with id: {} was updated", communication.getId());
    }

    private void checkCommunicationExistence(Communication communication) throws NoSuchCommunicationException {
        if (!communicationRepository.exists(communication.getId())) {
            logger.error("Cannot find communication with id: {}", communication);
            throw new NoSuchCommunicationException();
        }
    }

    @Override
    public void deleteCommunication(Communication communication) throws NoSuchCommunicationException {
        if (communication == null) {
            logger.error("Communication must not be null");
            return;
        }
        checkCommunicationExistence(communication);
        communication.setActive(false);
        communicationRepository.save(communication);
        logger.info("Communication with id: {} was updated", communication.getId());
    }

    @Override
    public List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask) {
      return communicationRepository.findFeedBacksByHomeTask(homeTask);
    }
}