package com.sytoss.mapper.education;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.UserAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountMapper extends BaseMapper<UserAccount, UserAccountDTO> {

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

    @Override
    public List<UserAccount> toListEntity(List<Object> dList) {
        return super.toListEntity(dList);
    }

    @Override
    public List<UserAccountDTO> toListDTO(List<UserAccount> userAccounts) {
        return super.toListDTO(userAccounts);
    }
}