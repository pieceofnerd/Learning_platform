package com.sytoss.mapper;

import com.sytoss.model.Media;
import com.sytoss.model.course.Course;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.MediaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper extends BaseMapper<Media, MediaDTO> {


    @Autowired
    public MediaMapper(ModelMapper mapper) {
        super(Media.class, MediaDTO.class);
    }

    @Override
    public Media toEntity(MediaDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public MediaDTO toDTO(Media entity) {
        return super.toDTO(entity);
    }
}