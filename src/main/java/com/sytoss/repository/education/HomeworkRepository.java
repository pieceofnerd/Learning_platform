package com.sytoss.repository.education;

import com.sytoss.model.Lookup;
import com.sytoss.model.course.HomeTask;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.enums.HomeworkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {


    List<Homework> findAllByHomeTaskAndHomeworkState(HomeTask homeTask, Lookup homeworkStatus);

    @Query("select h from Homework h where h.isActive = true and h.homeTask = ?1")
    List<Homework> findAllByHomeTaskAndActiveIsTrue(HomeTask homeTask);

    @Query("select h from Homework h where h.author = ?1")
    List<Homework> findAllByAuthorAndActiveIsTrue(UserAccount author);

    @Query("select h from Homework h where h.author = ?1 and h.isActive = true and h.homeTask = ?2")
    List<Homework> findAllByAuthorAndActiveIsTrueAndHomeTask(UserAccount author, HomeTask homeTask);

    @Query("select h from Homework h where h.homeTask = ?1")
    List<Homework> findAllByHomeTask(HomeTask homeTask);

    @Query("select h" +
            " from Homework h" +
            " where (h.author = ?1 and h.homeTask = ?2 and h.homeTask.deadlineDate <= current_date)" +
            "or (h.author = ?1 and h.homeTask = ?2 and h.homeworkState=?3)")
    Homework findHomeworkByAuthorAndHomeTask(UserAccount author, HomeTask homeTask, Lookup homeworkState);


    @Query("select h  from Homework h where h.author = ?1 and h.homeTask = ?2 and h.homeworkState=?3")
    Homework findProvenHomeworkByAuthorAndHomeTask(UserAccount author, HomeTask homeTask, Lookup homeworkState);
}
