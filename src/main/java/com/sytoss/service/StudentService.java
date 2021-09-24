package com.sytoss.service;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface StudentService {

    void rateCourse(UserAccount student, Course course, Integer rateValue);

    List<Course> findCoursesByStudentByFilter(UserAccount student, FilterUserAccountDTO filter);

    List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup);

    Purchase payCourse(UserAccount student, Course course);

    void returnCourse(UserAccount student, Course course);

    void joinStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception;

    void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception;
}
