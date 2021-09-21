package com.sytoss.mapper;

import com.sytoss.model.communication.Communication;
import com.sytoss.web.dto.CommunicationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunicationMapper extends BaseMapper<Communication, CommunicationDTO> {


    public CommunicationMapper() {
        super(Communication.class, CommunicationDTO.class);
    }

    @Override
    public Communication toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public CommunicationDTO toDTO(Communication entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<CommunicationDTO> toListDTO(List<Communication> communications) {
        return super.toListDTO(communications);
    }

    @Override
    public List<Communication> toListEntity(List<Object> communicationDTOS) {
        return super.toListEntity(communicationDTOS);
    }
}