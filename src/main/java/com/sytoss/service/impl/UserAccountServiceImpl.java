package com.sytoss.service.impl;

import com.sytoss.config.Constant;
import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_contet_exception.CommunicationNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.LookupName;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.LookupNameRepository;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.MediaRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.CommunicationService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
        private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private final CommunicationService communicationService;

    private final UserAccountRepository userAccountRepository;

    private final MediaRepository mediaRepository;

    private final LookupRepository lookupRepository;

    private final LookupNameRepository lookupNameRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, CommunicationService communicationService,
                                  MediaRepository mediaRepository, LookupRepository lookupRepository, LookupNameRepository lookupNameRepository) {
        this.userAccountRepository = userAccountRepository;
        this.communicationService = communicationService;
        this.mediaRepository = mediaRepository;
        this.lookupRepository = lookupRepository;
        this.lookupNameRepository = lookupNameRepository;
    }

    @Override
    public void registerUserAccount(UserAccount userAccount) throws EmailAlreadyExistsException, UserAccountNoContentException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            throw new UserAccountNoContentException("User is null");
        }

        validateEmail(userAccount);
        userAccount = userAccountRepository.save(userAccount);

    }

    private void validateEmail(UserAccount userAccount) throws EmailAlreadyExistsException {
        if (userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(userAccount.getEmail()) != null) {
            logger.error("User with email {} is already registered", userAccount.getEmail());
            throw new EmailAlreadyExistsException(String.format("User with email %s is already registered", userAccount.getEmail()));
        }
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, UserAccountNoContentException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            throw new UserAccountNoContentException("User is null");
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
    public void deleteUserAccount(UserAccount userAccount) throws NoSuchUserAccountException, UserAccountNoContentException {
        if (userAccount == null) {
            logger.error("user account must be not null");
            throw new UserAccountNoContentException("User is null");
        }
        UserAccount user = userAccountRepository.findOne(userAccount.getId());
        checkUserAccountExistence(userAccount);
        user.setDeleted(true);
        userAccountRepository.save(user);
    }

    @Override
    public void saveCV(Media media) throws MediaNoContentException {
        if (media == null) {
            logger.error("CV must be not null");
            throw new MediaNoContentException("CV is null");
        }
        mediaRepository.save(media);
    }

    @Override
    public void resetPassword(UserAccount userAccount, char[] oldPassword, char[] newPassword) throws Exception {
        if (userAccount == null) {
            logger.error("user account must be not null");
            return;
        }
        checkUserAccountExistence(userAccount);

        if (oldPassword == null) {
            logger.info("Old password is empty");
            throw new Exception("Old password is empty");
        }
        if (newPassword == null) {
            logger.info("New password is empty");
            throw new Exception("New password is empty");
        }
        if (Arrays.toString(newPassword).equals(Arrays.toString(oldPassword))) {
            logger.info("Passwords are the same");
            throw new Exception("Passwords are the same");
        }
        UserAccount user = userAccountRepository.findOne(userAccount.getId());

        if (!Arrays.toString(user.getPassword()).equals(Arrays.toString(oldPassword))) {
            logger.info("User password not equals old password");
            throw new Exception("User password not equals old password");
        }
        user.setPassword(newPassword);
        userAccountRepository.save(user);

    }

    @Override
    public void forgotPassword(String email) throws UserAccountNoContentException {
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(email);
        if (userAccount == null) {
            logger.error("Couldn't find user account by email {}", email);
            throw new UserAccountNoContentException("User is null");
        }

        //send email
        //TODO
    }

    @Override
    public void leaveComment(Communication comment) throws CommentNoContentException, CommunicationNoContentException {
        if (comment == null) {
            logger.error("Comment must not be null");
            throw new CommentNoContentException("Comment is null");
        }
        communicationService.createCommunication(comment);
    }

    @Override
    public void leaveMessage(Communication message) throws MessageNoContentException, CommunicationNoContentException {
        if (message == null) {
            logger.error("Message must not be null");
            throw new MessageNoContentException("Message is null");
        }
        communicationService.createCommunication(message);
    }

    @Override
    public List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter) {
        List<UserAccount> users = new ArrayList<>();
        switch (filter.getFilter()) {
            case FULL_NAME: {
                users.addAll(userAccountRepository.findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getFirstName(), filter.getSecondName()));
                break;
            }
            case FIRST_NAME: {
                users.addAll(userAccountRepository.findAllByFirstNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getFirstName()));
                break;
            }
            case SECOND_NAME: {
                users.addAll(userAccountRepository.findAllBySecondNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getSecondName()));
                break;
            }
            case DELETED: {
                //TODO
                break;
            }
        }
        return users;
    }

    private void checkUserAccountExistence(UserAccount userAccount) throws NoSuchUserAccountException {
        if (!userAccountRepository.exists(userAccount.getId())) {
            throw new NoSuchUserAccountException("Couldn't find user account with id: "+ userAccount.getId());
        }
    }

    private void addPreferenceTags(UserAccount userAccount){
        if(userAccount.getDiscriminatorValue().equals("3")){
        }
    }

}