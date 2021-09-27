package com.sytoss.controller;

import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.service.StudentService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.CourseDTO;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class StudentController {
    private final UserAccountService userAccountService;
    private final StudentService studentService;
    private final CommunicationMapper communicationMapper;
    private final PurchaseMapper purchaseMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final UserAccountMapper userAccountMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    public PurchaseDTO payForCourse(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);//TODO
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        PurchaseDTO purchaseDTO = null;
        try {
            purchaseDTO = purchaseMapper.toDTO(studentService.payCourse(student, studyGroup));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchaseDTO;
    }

    public void rateCourse(CourseDTO courseDTO, Integer rateValue) throws NoSuchCourseException {
        Course course = courseMapper.toEntity(courseDTO);
        studentService.rateCourse(course, rateValue);
    }

    public void returnCourse(UserAccount student, StudyGroup studyGroup) {

    }

    public void joinStudyGroup(UserAccount student, StudyGroup studyGroup) {

    }

    public void leaveStudyGroup(UserAccount student, StudyGroup studyGroup) {

    }
}
