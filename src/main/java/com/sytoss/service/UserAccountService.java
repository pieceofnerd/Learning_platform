package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;

import java.util.List;

public interface UserAccountService {

//    UserAccount findUserAccountById(Long id) throws Exception;

    List<UserAccountDTO> findByFilter(FilterDTO filter);

    boolean saveUserAccount(UserAccountSaveDTO userAccountSaveDTO);

    boolean updateUserAccount(UserAccountDTO userAccountDTO);

    boolean deleteUserAccount(UserAccountDTO userAccountDTO);

    boolean resetPassword(UserAccountDTO userAccountDTO, char[] newPassword);

    boolean forgotPassword(String email);

    boolean leaveComment(Communication comment);
}