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
    public void createComment(Communication communication) {
       //TODO
    }

    @Override
    public void createFeedback(Communication feedback) {
        //TODO
    }

    @Override
    public void updateFeedback(Communication feedback) {
        //TODO
    }

    @Override
    public void delete(Communication communication) {
        //TODO
    }

    @Override
    public List<Feedback> findFeedbacksByHomeTask(HomeTask homeTask){
        return  null;
    }
}