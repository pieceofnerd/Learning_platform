package com.sytoss.mapper;


import com.sytoss.model.course.Category;
import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.save.CategorySaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategoryDTO> {


    public CategoryMapper() {
        super(Category.class, CategoryDTO.class);
    }

    @Override
    public Category toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public CategoryDTO toDTO(Category entity) {
        return super.toDTO(entity);
    }
}
