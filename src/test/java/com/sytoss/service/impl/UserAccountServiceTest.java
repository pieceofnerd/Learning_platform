package com.sytoss.service.impl;

import com.sytoss.config.Config;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountServiceTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    private final StudyService studyService = context.getBean(StudyService.class);
    private final UserAccountService userAccountService = context.getBean(UserAccountService.class);
    private final StudyGroupService studyGroupService = context.getBean(StudyGroupService.class);

    private static final Long STUDY_ID = 13L;
    private static final Long STUDY_GROUP_ID = 12L;
    private static final Long STUDENT_ID = 10L;


    @Test
    void save_ShouldSaveUserAccountAndReturnTrue_WhenUserAccountNotNull() {
        //assertTrue(userAccountService.saveUserAccount(getUserAccount()));
    }

    @Test
    void save_ShouldReturnFalse_WhenUserAccountIsNull() {
        //assertFalse(userAccountService.saveUserAccount(null));
    }

    private UserAccount getUserAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName("Danil");
        userAccount.setSecondName("Butenko");
        userAccount.setEmail("butenko007@gmail.com");
        userAccount.setPassword("79n7b84v3rdcka".toCharArray());
        return userAccount;
    }
}