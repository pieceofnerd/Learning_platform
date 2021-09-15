package com.sytoss.mapper;

import com.sytoss.model.course.Topic;
import com.sytoss.web.dto.TopicDTO.TopicDTO;
import com.sytoss.web.dto.save.TopicSaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper extends BaseMapper<Topic, TopicDTO> {

    @Autowired
    public TopicMapper(ModelMapper mapper) {
        super(Topic.class, TopicDTO.class);
    }

    @Override
    public Topic toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public TopicDTO toDTO(Topic entity) {
        return super.toDTO(entity);
    }
}
