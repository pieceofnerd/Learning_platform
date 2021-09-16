package com.sytoss.mapper;

import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.UserAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper extends BaseMapper<UserAccount, UserAccountDTO>{

    protected UserAccountMapper() {
        super(UserAccount.class,UserAccountDTO.class);
    }
    @Override
    public UserAccount toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public UserAccountDTO toDTO(UserAccount entity) {
        return super.toDTO(entity);
    }
}