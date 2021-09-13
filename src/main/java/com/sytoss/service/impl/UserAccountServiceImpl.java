package com.sytoss.service.impl;

import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.UserAccountRepository;
import com.sytoss.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public boolean saveUserAccount(UserAccount userAccount) {
        return false;
    }

    @Override
    public boolean updateUserAccount(Long id, UserAccount userAccount) {
        return false;
    }

    @Override
    public boolean deleteUserAccount(UserAccount userAccount) {
        return false;
    }

    @Override
    public UserAccount findUserAccountById(Long id) throws Exception {
        if (!userAccountRepository.exists(id)) {
            throw new Exception("User with id = " + id + " not found!");
        }
        final UserAccount user = userAccountRepository.findOne(id);

        return user;
    }

    @Override
    public List<UserAccount> findAll() {
        return null;
    }
}