package com.sytoss.repository.education;

import com.sytoss.model.course.HomeTask;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {



    @Query("select h from Homework h where h.author = ?1")
    List<Homework> findAllByAuthorAndActiveIsTrue(UserAccount author);

    @Query("select h from Homework h where h.author = ?1 and h.isActive = true and h.homeTask = ?2")
    List<Homework>  findAllByAuthorAndActiveIsTrueAndHomeTask(UserAccount author, HomeTask homeTask);
  
   @Query("select h from Homework h where h.homeTask = ?1")
    List<Homework> findAllByHomeTask(HomeTask homeTask);


}
