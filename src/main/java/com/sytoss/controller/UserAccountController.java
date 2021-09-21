package com.sytoss.controller;

import com.sytoss.mapper.CommunicationMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.CommunicationDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;
    private final CommunicationMapper communicationMapper;

    public List<UserAccount> findUserAccountsByFilter(FilterUserAccountDTO filter) {
        return userAccountService.findUsersByFilter(filter);
    }

    public void saveUserAccount(UserAccountSaveDTO userAccountSaveDTO){
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountSaveDTO);
        //TODO
    }

    public void deleteUserAccount(UserAccountDTO userAccountDTO) {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        userAccountService.deleteUserAccount(userAccount);
    }

    public void updateUserAccount(UserAccountDTO userAccountDTO) {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        userAccountService.updateUserAccount(userAccount);
    }

    public void leaveComment(CommunicationDTO commentDTO) {
        final Communication comment = communicationMapper.toEntity(commentDTO);
        userAccountService.leaveComment(comment);
    }

    public void forgetPassword(String email) {
        //TODO
    }

    public void resetPassword(UserAccountDTO userAccountDTO, char[] newPassword) {
        //TODO
    }
}