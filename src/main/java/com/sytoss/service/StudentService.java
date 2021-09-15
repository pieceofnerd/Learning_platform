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

    Purchase payCourse(UserAccount student, Course course);

    boolean returnCourse(UserAccount student, Course course);

    boolean joinStudyGroup(UserAccount student, Course course);

    boolean leaveStudyGroup(UserAccount student, StudyGroup studyGroup);
}
