package com.sytoss.service.impl;

import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.UserAccountRepository;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.FilterDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper mapper;


    @Override
    public List<UserAccountDTO> findByFilter(FilterDTO filter) {
        return null;
    }

    @Override
    public boolean saveUserAccount(UserAccountSaveDTO userAccountSaveDTO) {
        if (userAccountSaveDTO == null)
            return false;
        UserAccount userAccount = mapper.toEntity(userAccountSaveDTO);
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean updateUserAccount(UserAccountDTO userAccountDTO) {
        if (userAccountDTO == null)
            return false;
        if (!userAccountRepository.exists(userAccountDTO.getId()))
            return false;
        UserAccount userAccount = mapper.toEntity(userAccountDTO);
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean deleteUserAccount(UserAccountDTO userAccountDTO) {
        if (userAccountDTO == null)
            return false;
        if (!userAccountRepository.exists(userAccountDTO.getId()))
            return false;
        UserAccount userAccount = mapper.toEntity(userAccountDTO);
        userAccount.setDeleted(true);
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean resetPassword(UserAccountDTO userAccountDTO, char[] newPassword) {
        if (userAccountDTO == null)
            return false;
        if (!userAccountRepository.exists(userAccountDTO.getId()))
            return false;
        UserAccount userAccount = userAccountRepository.findOne(userAccountDTO.getId());
        userAccount.setPassword(newPassword);
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public boolean forgotPassword(String email) {

        if (email.isEmpty()) {
            return false;
        }
        UserAccount userAccount = userAccountRepository.findUserAccountByEmail(email);
        if (userAccount == null)
            return false;
        //send email
        return true;
    }

    @Override
    public boolean leaveComment(Communication comment) {

        return false;
    }
}