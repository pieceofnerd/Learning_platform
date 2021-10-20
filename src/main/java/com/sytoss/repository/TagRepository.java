package com.sytoss.repository;

import com.sytoss.model.Tag;
import com.sytoss.model.course.Course;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByCourse(Course course);

    @Query("select t from Tag t where t.mentor = ?1 or t.student = ?1")
    List<Tag> findTagsByStudentOrMentor(UserAccount userAccount);

}
