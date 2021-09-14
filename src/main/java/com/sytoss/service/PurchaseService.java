package com.sytoss.service;

import com.sytoss.model.education.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase payCourse(Purchase purchase) throws Exception;

    boolean updatePurchase(Purchase purchase);

    List<Purchase> findPurchaseByFilter(); //TODO need to add arg

    boolean refundMoney(Purchase purchase);
}
