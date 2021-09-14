package com.sytoss.service.impl;

import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.UserAccountRepository;
import com.sytoss.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

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
        return userAccountRepository.findOne(id);
    }

    @Override
    public List<UserAccount> findAll() {
        return null;
    }
}