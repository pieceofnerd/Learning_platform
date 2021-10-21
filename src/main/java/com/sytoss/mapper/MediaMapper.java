package com.sytoss.mapper;

import com.sytoss.model.Media;
import com.sytoss.web.dto.MediaDTO;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Media> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<MediaDTO> toListDTO(List<Media> media) {
        return super.toListDTO(media);
    }
}