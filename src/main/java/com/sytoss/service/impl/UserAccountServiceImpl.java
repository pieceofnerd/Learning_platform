package com.sytoss.service.impl;

import com.sytoss.exception.EmailAlreadyExistsException;
import com.sytoss.exception.NoSuchUserAccountException;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.CommunicationService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private final UserAccountRepository userAccountRepository;

    private final CommunicationService communicationService;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, CommunicationService communicationService) {
        this.userAccountRepository = userAccountRepository;
        this.communicationService = communicationService;
    }

    @Override
    public void registerUserAccount(UserAccount userAccount) throws EmailAlreadyExistsException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            return;
        }

        validateEmail(userAccount);
        userAccountRepository.save(userAccount);
    }

    private void validateEmail(UserAccount userAccount) throws EmailAlreadyExistsException {
        if (userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(userAccount.getEmail()) != null) {
            logger.error("User with email {} is already registered", userAccount.getEmail());
            throw new EmailAlreadyExistsException();
        }
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, EmailAlreadyExistsException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            return;
        }
        checkUserAccountExistence(userAccount);

        UserAccount user = userAccountRepository.findOne(userAccount.getId());
        user.setFirstName(userAccount.getFirstName());
        user.setSecondName(userAccount.getSecondName());
        user.setBirthday(userAccount.getBirthday());
        user.setBio(userAccount.getBio());
        user.setPhoto(userAccount.getPhoto());
        user.setAddress(userAccount.getAddress());
        user.setEmail(userAccount.getEmail());

        user.setUpdatedDate(new Date());
        userAccountRepository.save(user);
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) throws NoSuchUserAccountException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            return;
        }
        UserAccount user = userAccountRepository.findOne(userAccount.getId());
        checkUserAccountExistence(userAccount);
        user.setDeleted(true);
        userAccountRepository.save(user);
    }

    @Override
    public void resetPassword(UserAccount userAccount, char[] oldPassword, char[] newPassword) throws Exception {
        if (userAccount == null) {
            logger.error("user account must be not null");
            return;
        }
        checkUserAccountExistence(userAccount);

        if(oldPassword == null){
            logger.info("Old password is empty");
            throw new Exception("Old password is empty");
        }
        if(newPassword == null){
            logger.info("New password is empty");
            throw new Exception("New password is empty");
        }
        if(Arrays.toString(newPassword).equals(Arrays.toString(oldPassword))){
            logger.info("Passwords are the same");
            throw new Exception("Passwords are the same");
        }
        UserAccount user = userAccountRepository.findOne(userAccount.getId());

        if(!Arrays.toString(user.getPassword()).equals(Arrays.toString(oldPassword))){
            logger.info("User password not equals old password");
            throw new Exception("User password not equals old password");
        }
        user.setPassword(newPassword);
        userAccountRepository.save(user);

    }

    @Override
    public void forgotPassword(String email) {
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(email);
        if (userAccount == null) {
            logger.error("Couldn't find user account by email {}", email);
        }

        //send email
        //TODO
    }

    @Override
    public void leaveComment(Communication comment) {
        if (comment == null) {
            logger.error("Comment must not be null");
            return;
        }
        communicationService.createCommunication(comment);
    }

    @Override
    public List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter) {
        switch (filter.getFilter()) {
            case FULL_NAME: {
                return userAccountRepository.findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getFirstName(), filter.getSecondName());
            }
            case FIRST_NAME: {
                return userAccountRepository.findAllByFirstNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getFirstName());
            }
            case SECOND_NAME: {
                return userAccountRepository.findAllBySecondNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getSecondName());
            }
            case DELETED: {
                //TODO
            }
        }
        return null;
    }

    private void checkUserAccountExistence(UserAccount userAccount) throws NoSuchUserAccountException {
        if (!userAccountRepository.exists(userAccount.getId())) {
            logger.error("Couldn't find user account with id: {}", userAccount.getId());
            throw new NoSuchUserAccountException();
        }
    }

}