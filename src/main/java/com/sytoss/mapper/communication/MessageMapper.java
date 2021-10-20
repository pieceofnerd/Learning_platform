package com.sytoss.mapper.communication;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.communication.Message;
import com.sytoss.web.dto.CommunicationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper extends BaseMapper<Message, CommunicationDTO> {
    MessageMapper() {
        super(Message.class,CommunicationDTO.class);
    }

    @Override
    public Message toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public CommunicationDTO toDTO(Message entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Message> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<CommunicationDTO> toListDTO(List<Message> messages) {
        return super.toListDTO(messages);
    }
}
