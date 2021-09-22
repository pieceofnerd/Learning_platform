package com.sytoss.service.impl;

import com.sytoss.config.Config;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.PurchaseRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.PurchaseService;
import com.sytoss.service.StudyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {
    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    private final PurchaseService purchaseService=context.getBean(PurchaseService.class);
    private final UserAccountRepository userAccountRepository = context.getBean(UserAccountRepository.class);
    private final PurchaseRepository purchaseRepository = context.getBean(PurchaseRepository.class);
    private final StudyGroupRepository studyGroupRepository= context.getBean(StudyGroupRepository.class);

    @Test
    void payCourse() {
            purchaseService.payCourse((Student) userAccountRepository.findOne(1L),studyGroupRepository.findOne(1L));
    }
}