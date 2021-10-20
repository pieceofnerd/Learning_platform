package com.sytoss.mapper.communication;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.communication.Comment;
import com.sytoss.web.dto.CommunicationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper extends BaseMapper<Comment, CommunicationDTO> {
    CommentMapper() {
        super(Comment.class, CommunicationDTO.class);
    }

    @Override
    public CommunicationDTO toDTO(Comment entity) {
        return super.toDTO(entity);
    }

    @Override
    public Comment toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<CommunicationDTO> toListDTO(List<Comment> comments) {
        return super.toListDTO(comments);
    }

    @Override
    public List<Comment> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }
}
