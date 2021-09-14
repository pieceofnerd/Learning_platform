package com.sytoss.service.impl;

import com.sytoss.model.education.Purchase;
import com.sytoss.repository.PurchaseRepository;
import com.sytoss.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;


    @Override
    public Purchase payCourse(Purchase purchase) throws Exception {
        if (purchase == null) {
            throw new Exception("Purchase is null");
        }
        final Purchase saved = purchaseRepository.save(purchase);
        return saved;
    }

    @Override
    public boolean updatePurchase(Purchase purchase) {
        if (purchase == null) {
            return false;
        }
        purchaseRepository.save(purchase);
        return true;
    }

    @Override
    public List<Purchase> findPurchaseByFilter() {
        return null;
    }

    @Override
    public boolean refundMoney(Purchase purchase) {
        return false;
    }
}