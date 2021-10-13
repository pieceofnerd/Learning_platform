package com.sytoss.util;

import com.sytoss.exception.no_such_exception.NoSuchPromotionException;
import com.sytoss.model.course.Promotion;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.PromotionRepository;
import com.sytoss.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.sytoss.model.enums.PromotionStatus.ACTIVE;
import static com.sytoss.model.enums.PromotionStatus.ANNOUNCED;

@Component
@RequiredArgsConstructor
public class PromotionSchedule {

    private final PromotionService promotionService;
    private final LookupRepository lookupRepository;
    private final PromotionRepository promotionRepository;

    //At 12:00 p.m. every day
    @Scheduled(cron = "0 12 * * ?")
    public void checkPromotionDates() throws NoSuchPromotionException {
        List<Promotion> promotions = promotionRepository.findAll();
        changePromotionState(promotions);

    }

    private void changePromotionState(List<Promotion> promotions) throws NoSuchPromotionException {
        Date date = new Date();
        for (Promotion promotion : promotions) {
            Date start = promotion.getStartDate();
            Date end = promotion.getEndDate();
            Long currentPromotionState = promotion.getPromotionState().getId();
            if (currentPromotionState.equals(ANNOUNCED.getValue())) {
                if (start.before(date) && end.after(date)) {
                    promotion.setPromotionState(lookupRepository.findOne(21L));
                    promotionService.updatePromotion(promotion);
                }
            }
            if (currentPromotionState.equals(ACTIVE.getValue())) {
                if (!end.after(date)) {
                    promotion.setPromotionState(lookupRepository.findOne(22L));
                    promotionService.updatePromotion(promotion);
                }
            }
        }
    }
}
