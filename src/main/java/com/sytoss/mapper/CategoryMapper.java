package com.sytoss.mapper;


import com.sytoss.model.course.Category;
import com.sytoss.web.dto.save.CategorySaveDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends BaseMapper<Category, CategorySaveDTO> {

    @Autowired
    public CategoryMapper(ModelMapper mapper) {
        super(Category.class, CategorySaveDTO.class);
    }

    @Override
    public Category toEntity(CategorySaveDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public CategorySaveDTO toDTO(Category entity) {
        return super.toDTO(entity);
    }
}
