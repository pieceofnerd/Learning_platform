package com.sytoss.mapper;

import com.sytoss.model.Media;
import com.sytoss.web.dto.MediaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper extends BaseMapper<Media, MediaDTO> {


    public MediaMapper() {
        super(Media.class, MediaDTO.class);
    }

    @Override
    public Media toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public MediaDTO toDTO(Media entity) {
        return super.toDTO(entity);
    }
}