package com.sytoss.repository.course;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.active = true")
    List<Course> findCoursesByActiveIsTrue();

    List<Course> findDistinctFirst10ByActiveIsTrueOrderByCreatedDateDesc();

    @Query("select c from Course c " +
            "left join fetch c.topics " +
            "where c.id = ?1")
    Course findById(Long id);

    @Query("select c from Course c where c.name = ?1 and c.active = true")
    Course findCourseByNameAndActiveIsTrue(String name);

    @Query("select c " +
            "from Course c inner join Price  p on c = p.course " +
            "where p.priceType=?1 and p.cost>=?2 and p.cost<=?3 and c.active=true")
    List<Course> findActiveCourseByPriceRange(Lookup priceType, BigDecimal lowPrice, BigDecimal highPrice);

}
