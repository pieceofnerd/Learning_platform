package com.sytoss.controller;

import com.sytoss.exception.EmailAlreadyExistsException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.mapper.CommunicationMapper;
import com.sytoss.mapper.PurchaseMapper;
import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.service.StudentService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.CommunicationDTO;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import com.sytoss.web.dto.save.PurchaseSaveDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final StudentService studentService;
    private final UserAccountMapper userAccountMapper;
    private final CommunicationMapper communicationMapper;
    private final PurchaseMapper purchaseMapper;
    private final StudyGroupMapper studyGroupMapper;



    public List<UserAccount> findUserAccountsByFilter(FilterUserAccountDTO filter) {
        return userAccountService.findUsersByFilter(filter);
    }

    public void registerUserAccount(UserAccountSaveDTO userAccountSaveDTO) {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountSaveDTO);
        try {
            userAccountService.registerUserAccount(userAccount);
        } catch (EmailAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAccount(UserAccountDTO userAccountDTO) throws NoSuchUserAccountException {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        userAccountService.deleteUserAccount(userAccount);
    }

    public void updateUserAccount(UserAccountDTO userAccountDTO) throws EmailAlreadyExistsException, NoSuchUserAccountException {
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