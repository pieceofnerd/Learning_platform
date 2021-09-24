package com.sytoss.service.impl;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.service.StudentService;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {


    @Override
    public void rateCourse(UserAccount student, Course course, Integer rateValue) {

    }

    @Override
    public List<Course> findCoursesByStudentByFilter(UserAccount student, FilterUserAccountDTO filter) {
        return null;
    }

    @Override
    public List<UserAccount> findStudentsByStudyGroup(StudyGroup studyGroup) {
        return null;
    }

    @Override
    public Purchase payCourse(UserAccount student, Course course) {
        return null;
    }

    @Override
    public void returnCourse(UserAccount student, Course course) {
        //TODO
    }

    @Override
    public void joinStudyGroup(UserAccount student, Course course) {
        //TODO
    }

    @Override
    public void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) {
        //TODO
    }
}