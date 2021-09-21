package com.sytoss.repository.communication;

import com.sytoss.model.Lookup;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.education.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    @Query("select c from Feedback  c where c.homework=?1")
    Communication findFeedbackByHomework(Homework homework);

    @Query("select c from Comment c where c.active=true order by c.sendDate")
    List<Communication> findByOrderBySendDate();

    @Query("select c from Comment c where c.active=true  order by c.sendDate DESC")
    List<Communication> findByOrderBySendDateDesc();

}
