package com.sytoss.repository.communication;

import com.sytoss.model.Lookup;
import com.sytoss.model.communication.Comment;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.communication.Feedback;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.education.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    @Query("select c from Feedback  c where c.homework=?1")
    Communication findFeedbackByHomework(Homework homework);

    @Query("select f from Feedback f inner join Homework  h where h.homeTask=?1")
    List<Feedback> findFeedBacksByHomeTask(HomeTask homeTask);

    @Query("select c from Comment c where c.active=true  and c.lesson.id=?1 order by c.sendDate")
    List<Communication> findByLessonIdOrderBySendDate(Long lessonId);

    @Query("select c from Comment c where c.active=true and c.lesson.id=?1 order by c.sendDate DESC")
    List<Communication> findByLessonIdOrderBySendDateDesc(Long lessonId);



}
