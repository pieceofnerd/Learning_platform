package com.sytoss.controller;

import com.sytoss.exception.NoSuchCourseException;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.service.StudentService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.*;
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
    private final StudyMapper studyMapper;


    public List<StudyDTO> findStudiesByStudent(UserAccountDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        List<Study> studies = null;
        try {
            studies = studentService.findStudiesByStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studyMapper.toListDTO(studies);
    }

    public PurchaseDTO payForCourse(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);
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

    public void returnCourse(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        studentService.returnCourse(student, studyGroup);
    }

    public List<PurchaseDTO> findPurchaseByStudent(UserAccountDTO userAccountDTO) {
        Student student = studentMapper.toEntity(userAccountDTO);
        return purchaseMapper.toListDTO(studentService.findPurchaseByStudent(student));
    }

    public void joinStudyGroup(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        studentService.joinStudyGroup(student, studyGroup);
    }

    public void leaveStudyGroup(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        studentService.leaveStudyGroup(student, studyGroup);
    }

}
