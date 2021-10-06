package com.sytoss.service;

import com.sytoss.exception.no_contet_exception.StudyGroupNoContentException;
import com.sytoss.exception.no_contet_exception.UserAccountNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchPurchaseException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.filter.FilterPurchaseDTO;

import java.util.List;

public interface PurchaseService {

    Purchase payCourse(Student student, StudyGroup studyGroup) throws UserAccountNoContentException, StudyGroupNoContentException, NoSuchStudyGroupException, NoSuchUserAccountException;

    void updatePurchase(Purchase purchase) throws NoSuchPurchaseException;

    List<Purchase> findPurchasesByStudent(Student student);

    List<Purchase> findPurchaseByFilter(FilterPurchaseDTO filter);

    boolean refundMoney(Purchase purchase);
}
