package com.sytoss.mapper;

import com.sytoss.model.Media;
import com.sytoss.web.dto.save.MediaSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper extends BaseMapper<Media, MediaSaveDTO> {


    @Autowired
    public MediaMapper(ModelMapper mapper) {
        super(Media.class, MediaSaveDTO.class);
    }

    @Override
    public Media toEntity(MediaSaveDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public MediaSaveDTO toDTO(Media entity) {
        return super.toDTO(entity);
    }
}