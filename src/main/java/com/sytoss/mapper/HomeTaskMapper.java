package com.sytoss.mapper;

import com.sytoss.model.course.HomeTask;
import com.sytoss.model.course.Lesson;
import com.sytoss.web.dto.HomeTaskDTO;
import com.sytoss.web.dto.LessonDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomeTaskMapper extends BaseMapper<HomeTask, HomeTaskDTO> {
    protected HomeTaskMapper() {
        super(HomeTask.class, HomeTaskDTO.class);
    }

    @Override
    public HomeTaskDTO toDTO(HomeTask entity) {
        return super.toDTO(entity);
    }

    @Override
    public HomeTask toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<HomeTask> toListEntity(List<Object> homeTasksDTO) {
        return super.toListEntity(homeTasksDTO);
    }

    @Override
    public List<HomeTaskDTO> toListDTO(List<HomeTask> lessons) {
        return super.toListDTO(lessons);
    }
}
