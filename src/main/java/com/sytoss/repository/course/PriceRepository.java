package com.sytoss.repository.course;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price,Long> {

    @Query("select p from Price p where p.course = ?1 and p.priceType = ?2")
    Price findByCourseAndPriceType(Course course, Lookup priceType);

}
