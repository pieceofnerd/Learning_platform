package com.sytoss.service;

import com.sytoss.exception.NoSuchPurchaseException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface PurchaseService {

    Purchase payCourse(Student student, StudyGroup studyGroup);

    void updatePurchase(Purchase purchase) throws NoSuchPurchaseException;

    List<Purchase> findPurchaseByFilter(UserAccount student, FilterDTO filter);

    boolean refundMoney(Purchase purchase);
}
