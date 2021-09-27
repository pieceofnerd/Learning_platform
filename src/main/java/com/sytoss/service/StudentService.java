package com.sytoss.service;

import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface StudentService {

    void rateCourse( Course course, Integer rateValue) throws NoSuchCourseException;


    //TODO
    List<Course> findCoursesByStudentByFilter(UserAccount student, FilterUserAccountDTO filter);

    //TODO
    List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup);

    List<Purchase> findPurchaseByStudent(UserAccount student);

    Purchase payCourse(Student student, StudyGroup studyGroup) throws Exception;

    void returnCourse(UserAccount student, StudyGroup studyGroup) throws Exception;

    void joinStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception;

    void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) throws Exception;
}
