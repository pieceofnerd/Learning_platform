package com.sytoss.util;


import com.sytoss.controller.PurchaseController;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterPurchaseDTO;
import com.sytoss.web.dto.filter.FilterStudyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sytoss.util.MenuUtils.*;
import static com.sytoss.util.MenuUtils.scanInt;

@Service
@RequiredArgsConstructor
public class PurchaseMenu {

    private final PurchaseController purchaseController;
    private final StudyGroupRepository studyGroupRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Pay course"
        );

        long studentId;
        long studyGroupId;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                purchaseController.pay(
                        (Student) userAccountRepository.findOne(studentId)
                        , studyGroupRepository.findById(studyGroupId)
                );
                break;
        }
    }

    private FilterPurchaseDTO selectFilter(int i) throws Exception {
        FilterPurchaseDTO filter = new FilterPurchaseDTO();
        switch (i) {
            case 1:

                break;
            default:
                throw new Exception("FILTER ERROR");
        }
        return filter;
    }
}
