package com.sytoss.service;

import com.sytoss.exception.EmailAlreadyExistsException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter);

    void registerUserAccount(UserAccount userAccount) throws EmailAlreadyExistsException;

    void updateUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, EmailAlreadyExistsException;

    void deleteUserAccount(UserAccount userAccount) throws NoSuchUserAccountException;

    void resetPassword(UserAccount userAccount, char[] newPassword) throws NoSuchUserAccountException;

    void forgotPassword(String email);

    void leaveComment(Communication comment);
}