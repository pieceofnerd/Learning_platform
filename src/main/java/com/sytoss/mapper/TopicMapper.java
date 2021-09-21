package com.sytoss.mapper;

import com.sytoss.model.course.Topic;
import com.sytoss.web.dto.TopicDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicMapper extends BaseMapper<Topic, TopicDTO> {

    public TopicMapper() {
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

    @Override
    public List<Topic> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<TopicDTO> toListDTO(List<Topic> topics) {
        return super.toListDTO(topics);
    }
}
