package com.sytoss.service;

import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;

import java.util.List;

public interface UserAccountService {

    boolean saveUserAccount(UserAccount userAccount);

    boolean updateUserAccount(Long id, UserAccount userAccount);

    boolean deleteUserAccount(UserAccount userAccount);

    UserAccount findUserAccountById(Long id) throws Exception;

    List<UserAccount> findAll();
}