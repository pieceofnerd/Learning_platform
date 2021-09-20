package com.sytoss.repository.course;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    @Query("select t from Topic t where t.active = true and t.course = ?1")
    List<Topic> findTopicsByActiveIsTrueAndCourse(Course course);
}
