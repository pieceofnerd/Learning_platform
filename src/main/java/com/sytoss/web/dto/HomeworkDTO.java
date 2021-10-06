package com.sytoss.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDTO {

    private Long id;

    private UserAccountDTO author;

    private LookupDTO homeworkState;

    private String answerPath;

    private Date fulfillmentDate;

    //List<Message>
    private List<CommunicationDTO> dialog;

    //List<Feedback>
    private List<CommunicationDTO> feedbacks;

    private Date createdDate;

    private Date updatedDate;
}