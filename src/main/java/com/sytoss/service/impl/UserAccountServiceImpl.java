package com.sytoss.service.impl;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.CommunicationService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final CommunicationService communicationService;


    @Override
    public List<UserAccount> findUsersByFilter(FilterUserAccountDTO filter) {
        List<UserAccount> users = new ArrayList<>();
        switch (filter.getFilter()) {
            case FULL_NAME: {
                users.addAll(userAccountRepository.findAllByFirstNameStartingWithIgnoreCaseAndSecondNameStartingWithIgnoreCaseAndDeletedIsFalse(filter.getFirstName(),filter.getSecondName()));
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

//    @Override
//    public UserAccount findUserAccountById(Long id) throws Exception {
//        if (!userAccountRepository.exists(id)) {
//            throw new Exception("User with id = " + id + " not found");
//        }
//        UserAccount userAccount = userAccountRepository.findOne(id);
//        return userAccount;
//    }

    @Override
    public boolean saveUserAccount(UserAccount userAccount) {
        if (userAccount == null)
            return false;
        if (userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(userAccount.getEmail()) != null)
            return false;

        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        if (userAccount == null)
            return false;
        if (!userAccountRepository.exists(userAccount.getId()))
            return false;
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean deleteUserAccount(UserAccount userAccount) {
        if (userAccount == null)
            return false;
        if (!userAccountRepository.exists(userAccount.getId()))
            return false;
        userAccount.setDeleted(true);
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean resetPassword(UserAccount userAccount, char[] newPassword) {
        if (userAccount == null)
            return false;
        if (!userAccountRepository.exists(userAccount.getId()))
            return false;

        userAccount.setPassword(newPassword);
        userAccountRepository.save(userAccount);
        //TODO
        return true;
    }

    @Override
    public boolean forgotPassword(String email) {
        if (email.isEmpty()) {
            return false;
        }
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailAndDeletedIsFalse(email);
        if (userAccount == null)
            return false;
        //send email
        //TODO
        return true;
    }

    @Override
    public boolean leaveComment(Communication comment) {
        if (comment == null)
            return false;
        return communicationService.createComment(comment);
    }

}