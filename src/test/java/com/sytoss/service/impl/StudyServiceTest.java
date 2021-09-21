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

class StudyServiceTest {
    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    private final StudyService studyService = context.getBean(StudyService.class);
    private final UserAccountService userAccountService = context.getBean(UserAccountService.class);
    private final StudyGroupService studyGroupService = context.getBean(StudyGroupService.class);

    private static final Long STUDY_ID = 13L;
    private static final Long STUDY_GROUP_ID = 12L;
    private static final Long STUDENT_ID = 10L;

    @Test
    void save_ShouldSaveStudyAndReturnTrue_WhenUserAccountAndStudyGroupNotNull() throws Exception {
        assertTrue(studyService.saveStudy(getStudent(), getStudyGroup()));
    }

    @Test
    void save_ShouldReturnFalse_WhenUserAccountAndStudyGroupIsNull() throws Exception {

        assertFalse(studyService.saveStudy(null, getStudyGroup()));
        assertFalse(studyService.saveStudy(getStudent(), null));
    }

    @Test
    void delete_ShouldDeleteStudy_WhenStudyNotNull() throws Exception {
        assertTrue(studyService.deleteStudy(getStudy()));
    }

    @Test()
    void delete_ShouldReturnFalse_WhenStudyIsNull() {
        assertFalse(studyService.deleteStudy(null)
        );
    }

    private Study getStudy() throws Exception {
        return studyService.findStudyById(STUDY_ID);
    }

    private StudyGroup getStudyGroup() throws Exception {
        return studyGroupService.findStudyGroupById(STUDY_GROUP_ID);
    }

    private UserAccount getStudent() throws Exception {
        return userAccountService.findUserAccountById(STUDENT_ID);
    }
}