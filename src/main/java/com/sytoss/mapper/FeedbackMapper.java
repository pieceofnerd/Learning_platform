package com.sytoss.mapper;

import com.sytoss.model.communication.Feedback;
import com.sytoss.web.dto.CommunicationDTO;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper extends BaseMapper<Feedback, CommunicationDTO> {
    FeedbackMapper() {
        super(Feedback.class,CommunicationDTO.class);
    }

    @Override
    public Feedback toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public CommunicationDTO toDTO(Feedback entity) {
        return super.toDTO(entity);
    }
}
