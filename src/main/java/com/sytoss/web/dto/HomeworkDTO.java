package com.sytoss.web.dto;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.communication.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
public class HomeworkDTO {

    private UserAccountDTO author;

    private String homeworkState;

    private String answerPath;

    private Date fulfillmentDate;

    private List<Message> dialog;

    private List<Feedback> feedbacks;

    private Date createdDate;

    private Date updatedDate;
}