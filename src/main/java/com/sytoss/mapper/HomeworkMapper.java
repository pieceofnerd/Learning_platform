package com.sytoss.mapper;

import com.sytoss.model.education.Homework;
import com.sytoss.web.dto.HomeworkDTO;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class HomeworkMapper extends BaseMapper<Homework, HomeworkDTO> {
    HomeworkMapper() {
        super(Homework.class, HomeworkDTO.class);
    }

    @Override
    public HomeworkDTO toDTO(Homework entity) {
        return super.toDTO(entity);
    }

    @Override
    public Homework toEntity(Object dto) {
        return super.toEntity(dto);
    }
}
