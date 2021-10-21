package com.sytoss.mapper;

import com.sytoss.model.Tag;
import com.sytoss.web.dto.TagDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TagMapper extends BaseMapper<Tag, TagDTO> {

    public TagMapper() {
        super(Tag.class, TagDTO.class);
    }

    @Override
    public Tag toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public TagDTO toDTO(Tag entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<Tag> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<TagDTO> toListDTO(List<Tag> media) {
        return super.toListDTO(media);
    }

}
