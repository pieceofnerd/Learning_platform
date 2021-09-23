package com.sytoss.service;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.FilterDTO;

import java.util.List;

public interface StudentService {

    void rateCourse(UserAccount student, Course course, Integer rateValue);

    List<Course> findCoursesByStudentByFilter(UserAccount student, FilterDTO filter);

    List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup);

    Purchase payCourse(UserAccount student, Course course);

    void returnCourse(UserAccount student, Course course);

    void joinStudyGroup(UserAccount student, Course course);

    void leaveStudyGroup(UserAccount student, StudyGroup studyGroup);
}
