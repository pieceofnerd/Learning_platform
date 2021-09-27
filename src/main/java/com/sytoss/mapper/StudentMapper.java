package com.sytoss.mapper;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.course.Category;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.CategoryDTO;
import com.sytoss.web.dto.UserAccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper extends BaseMapper<Student, UserAccountDTO> {

    protected StudentMapper()  {
        super(Student.class,UserAccountDTO.class);
    }

    @Override
    public Student toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public UserAccountDTO toDTO(Student entity) {
        return super.toDTO(entity);
    }
}
