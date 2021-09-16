package com.sytoss.service;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface UserAccountService {

//    UserAccount findUserAccountById(Long id) throws Exception;

    List<UserAccount> findByFilter(FilterDTO filter);

    boolean saveUserAccount(UserAccount userAccount);

    boolean updateUserAccount(Long id, UserAccount userAccount);

    boolean deleteUserAccount(UserAccount userAccount);

    boolean resetPassword(Long id);

    boolean forgotPassword(Long id);

    boolean leaveComment(Communication comment);
}