package com.sytoss.service;

import com.sytoss.exception.NoSuchPurchaseException;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.filter.FilterPurchaseDTO;

import java.util.List;

public interface PurchaseService {

    Purchase payCourse(Student student, StudyGroup studyGroup) throws Exception;

    void updatePurchase(Purchase purchase) throws NoSuchPurchaseException;

    List<Purchase> findPurchasesByStudent(Student student);

    List<Purchase> findPurchaseByFilter(FilterPurchaseDTO filter);

    boolean refundMoney(Purchase purchase) throws Exception;
}
