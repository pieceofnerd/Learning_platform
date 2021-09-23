package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {

    List<CourseRating> findCourseRatingByCourse(Course course);
}
