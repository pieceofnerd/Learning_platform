package com.sytoss.service.impl;

import com.sytoss.config.Config;
import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.service.CourseService;
import com.sytoss.service.StudyGroupService;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class StudyServiceTest {

    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    private final StudyService studyService = context.getBean(StudyService.class);
    private final StudyRepository studyRepository=  context.getBean(StudyRepository.class);
    private  final UserAccountService userAccountService= context.getBean(UserAccountService.class);
    private  final StudyGroupService studyGroupService= context.getBean(StudyGroupService.class);

    @Test
    void updateAssessment() throws Exception {
        studyGroupService.findStudentsByStudyGroup(studyGroupService.findStudyGroupById(1L));
//        studyService.updateAssessment(userAccountService.findUserAccountById(1L),studyGroupService.findStudyGroupById(1L));
    }

}