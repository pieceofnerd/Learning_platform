package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course,Long> {


    @Query("select c from Course c where c.active = true")
    List<Course> findCoursesByActiveIsTrue();

    List<Course> findCoursesByActiveIsTrueaAndName(String name);

}
