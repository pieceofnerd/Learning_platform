package com.sytoss.service;

import com.sytoss.exception.*;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.NoSuchCourseException;
import com.sytoss.exception.no_such_exception.NoSuchStudyException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;

import java.util.List;

public interface StudentService {

    void rateCourse(Course course, Integer rateValue) throws NoSuchCourseException, CourseNoContentException, CourseNoContentException, NoSuchCourseException;

    List<Study> findStudiesByStudent(Student student) throws NoSuchUserAccountException, UserAccountNoContentException;

    List<Purchase> findPurchaseByStudent(Student student);

    Purchase payCourse(Student student, StudyGroup studyGroup) throws CourseNotPaidException, UserAccountNoContentException, PurchaseNoContentException, StudyGroupNoContentException, NoSuchStudyGroupException, NoSuchUserAccountException, NoSuchStudyGroupException, NoSuchUserAccountException;

    void returnCourse(Student student, StudyGroup studyGroup) throws NoSuchStudyException, StudyNoContentException, StudyGroupNoContentException;

    void joinStudyGroup(Student student, StudyGroup studyGroup) throws PurchaseNoContentException, CourseNotPaidException, UserAccountNoContentException, StudyGroupNoContentException, UserAccountNoContentException, StudyGroupNoContentException;

    void leaveStudyGroup(Student student, StudyGroup studyGroup) throws NoSuchStudyException, StudyNoContentException, StudyGroupNoContentException, NoSuchStudyException, StudyNoContentException;
}
