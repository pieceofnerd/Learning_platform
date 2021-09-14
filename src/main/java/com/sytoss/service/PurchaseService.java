package com.sytoss.service;

import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface PurchaseService {

    Purchase payCourse(Purchase purchase) throws Exception;

    boolean updatePurchase(Purchase purchase);

    List<Purchase> findPurchaseByFilter(UserAccount student, FilterDTO filter);
            

    boolean refundMoney(Purchase purchase);
}
