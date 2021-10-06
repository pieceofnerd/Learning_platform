package com.sytoss.service;

import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter);

    void registerUserAccount(UserAccount userAccount) throws EmailAlreadyExistsException, UserAccountNoContentException;

    void updateUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, EmailAlreadyExistsException, UserAccountNoContentException;

    void deleteUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, UserAccountNoContentException;

    void resetPassword(UserAccount userAccount, char[] oldPassword, char[] newPassword) throws Exception;

    void saveCV(Media media) throws MediaNoContentException;

    void forgotPassword(String email) throws UserAccountNoContentException;

    void leaveComment(Communication comment) throws CommentNoContentException, CommunicationNoContentException;

    void leaveMessage(Communication message) throws MessageNoContentException, CommunicationNoContentException;
}