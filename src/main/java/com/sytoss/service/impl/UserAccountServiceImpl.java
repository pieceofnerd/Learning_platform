package com.sytoss.service.impl;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.UserAccountRepository;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.FilterDTO;
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
    public List<UserAccount> findByFilter(FilterDTO filter) {
        return null;
    }

    @Override
    public boolean saveUserAccount(UserAccount userAccount) {
        if (userAccount == null)
            return false;
        userAccountRepository.save(userAccount);
        return true;
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
    public boolean resetPassword(Long id) {
        return false;
    }

    @Override
    public boolean forgotPassword(Long id) {
        return false;
    }

    @Override
    public boolean leaveComment(Communication comment) {
        return false;
    }
}