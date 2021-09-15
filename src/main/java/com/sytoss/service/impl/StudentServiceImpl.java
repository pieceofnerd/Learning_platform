package com.sytoss.service.impl;

import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.repository.UserAccountRepository;
import com.sytoss.service.StudentService;
import com.sytoss.web.dto.FilterDTO;
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
    public List<Course> findCoursesByStudentByFilter(UserAccount student, FilterDTO filter) {
        return null;
    }

    @Override
    public Purchase payCourse(UserAccount student, Course course) {
        return null;
    }

    @Override
    public boolean returnCourse(UserAccount student, Course course) {
        return false;
    }

    @Override
    public boolean joinStudyGroup(UserAccount student, Course course) {
        return false;
    }

    @Override
    public boolean leaveStudyGroup(UserAccount student, StudyGroup studyGroup) {
        return false;
    }
}