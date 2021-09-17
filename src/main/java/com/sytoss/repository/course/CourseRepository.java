package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query("select c from Course c where c.active = true")
    List<Course> findCoursesByActiveIsTrue();

    List<Course> findDistinctFirst10ByOrderByCreatedDateDesc();

    @Query("select с  from  Course с inner join Price on Course = Price.course where Price .priceType=?1 and Price .cost>=?2 and Price.cost>=?3")
    List<Course> findCourseByPriceRange(Long priceType, BigDecimal lowPrice, BigDecimal highPrice);

}
