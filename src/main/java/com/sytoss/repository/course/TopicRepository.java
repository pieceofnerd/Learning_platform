package com.sytoss.repository.course;

import com.sytoss.model.course.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
