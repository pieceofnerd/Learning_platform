package com.sytoss.repository.communication;

import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.education.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    @Query("select c from Feedback  c where c.homework=?1")
    Communication findFeedbackByHomework(Homework homework);

}
