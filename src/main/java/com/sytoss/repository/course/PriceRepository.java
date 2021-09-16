package com.sytoss.repository.course;

import com.sytoss.model.course.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {
}
