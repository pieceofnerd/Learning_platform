package com.sytoss.repository.course;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.education.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeTaskRepository extends JpaRepository<HomeTask, Long> {

}
