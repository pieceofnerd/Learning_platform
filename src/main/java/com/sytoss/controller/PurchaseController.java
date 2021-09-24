package com.sytoss.controller;


import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.user.Student;
import com.sytoss.service.PurchaseService;
import com.sytoss.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    public void pay(Student student, StudyGroup studyGroup){
        try {
            purchaseService.payCourse(student, studyGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
