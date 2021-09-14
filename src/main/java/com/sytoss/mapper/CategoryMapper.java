package com.sytoss.mapper;


import com.sytoss.model.course.Category;
import com.sytoss.model.course.Course;
import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.CourseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryDTO> {

    @Autowired
    public CategoryMapper(ModelMapper mapper) {
        super(Category.class, CategoryDTO.class);
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public CategoryDTO toDTO(Category entity) {
        return super.toDTO(entity);
    }
}
