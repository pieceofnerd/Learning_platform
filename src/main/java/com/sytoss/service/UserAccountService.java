package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface UserAccountService {

//    UserAccount findUserAccountById(Long id) throws Exception;

    List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter);

    boolean saveUserAccount(UserAccount userAccount);

    boolean updateUserAccount(UserAccount userAccount);

    boolean deleteUserAccount(UserAccount userAccount);

    boolean resetPassword(UserAccount userAccount, char[] newPassword);

    boolean forgotPassword(String email);

    void leaveComment(Communication comment);
}