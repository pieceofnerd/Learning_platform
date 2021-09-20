package com.sytoss.service.impl;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;
import com.sytoss.service.CommunicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CommunicationServiceImpl implements CommunicationService {


    @Override
    public boolean createComment(Communication communication) {
        return false;
    }

    @Override
    public boolean createFeedback(Communication feedback) {
        return false;
    }

    @Override
    public boolean updateFeedback(Communication feedback) {
        return false;
    }

    @Override
    public boolean delete(Communication communication) {
        return false;
    }

    @Override
    public List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask){
        return  null;
    }
}