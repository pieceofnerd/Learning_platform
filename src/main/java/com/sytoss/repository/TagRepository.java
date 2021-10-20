package com.sytoss.repository;

import com.sytoss.model.Tag;
import com.sytoss.model.course.Course;
import com.sytoss.model.education.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByCourse(Course course);

    List<Tag> findTagsByMentor(UserAccount mentor);

    List<Tag> findTagsByStudent(UserAccount student);
}
