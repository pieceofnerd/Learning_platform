package com.sytoss.repository.course;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {

    @Query("select p from  Promotion  p where p.startDate>=?1 and p.startDate<=?2")
    List<Promotion> findPromotionsByTimePeriod(Date startDate, Date endDate);

    @Query("select p from Promotion p where p.promotionState = ?1")
    List<Promotion> findPromotionsByPromotionState(Lookup promotionState);

}
