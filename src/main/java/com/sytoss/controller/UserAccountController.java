package com.sytoss.controller;

import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.CommentNoContentException;
import com.sytoss.exception.no_contet_exception.MediaNoContentException;
import com.sytoss.exception.no_contet_exception.MessageNoContentException;
import com.sytoss.exception.no_contet_exception.UserAccountNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.mapper.*;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Message;
import com.sytoss.model.education.UserAccount;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import com.sytoss.web.dto.save.CommunicationSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import com.sytoss.web.dto.update.UserAccountUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAccountController {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;
    private final MediaMapper mediaMapper;
    private final CommentMapper commentMapper;
    private final MessageMapper messageMapper;


    //TODO
    public List<UserAccount> findUserAccountsByFilter(FilterUserAccountDTO filter) {
        return userAccountService.findUsersByFilter(filter);
    }

    public void registerUserAccount(UserAccountSaveDTO userAccountSaveDTO) {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountSaveDTO);
        try {
            userAccountService.registerUserAccount(userAccount);
        } catch (EmailAlreadyExistsException | UserAccountNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void saveCV(MediaSaveDTO mediaSaveDTO) {
        Media cv = mediaMapper.toEntity(mediaSaveDTO);
        try {
            userAccountService.saveCV(cv);
        } catch (MediaNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteUserAccount(UserAccountUpdateDTO userAccountUpdateDTO) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountUpdateDTO);
        try {
            userAccountService.deleteUserAccount(userAccount);
        } catch (UserAccountNoContentException | NoSuchUserAccountException e) {
            logger.error(e.getMessage());
        }
    }

    public void updateUserAccount(UserAccountUpdateDTO userAccountDTO) {
        final UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        try {
            userAccountService.updateUserAccount(userAccount);
        } catch (UserAccountNoContentException | NoSuchUserAccountException | EmailAlreadyExistsException e) {
            logger.error(e.getMessage());
        }
    }

    public void leaveComment(CommunicationSaveDTO commentDTO) {
        final Comment comment = commentMapper.toEntity(commentDTO);
        try {
            userAccountService.leaveComment(comment);
        } catch (CommentNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void leaveMessage(CommunicationSaveDTO commentDTO) {
        final Message message = messageMapper.toEntity(commentDTO);
        try {
            userAccountService.leaveMessage(message);
        } catch (MessageNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void resetPassword(UserAccountUpdateDTO userAccountDTO, char[] oldPassword, char[] newPassword) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);

        try {
            userAccountService.resetPassword(userAccount, oldPassword, newPassword);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void forgetPassword(String email) {
        //TODO
    }
}