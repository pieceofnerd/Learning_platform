package com.sytoss.controller;

import com.sytoss.exception.CourseNotPaidException;
import com.sytoss.exception.no_contet_exception.*;
import com.sytoss.exception.no_such_exception.NoSuchCourseException;
import com.sytoss.exception.no_such_exception.NoSuchStudyException;
import com.sytoss.exception.no_such_exception.NoSuchStudyGroupException;
import com.sytoss.exception.no_such_exception.NoSuchUserAccountException;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.user.Student;
import com.sytoss.service.StudentService;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
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
        } catch (NoSuchUserAccountException | UserAccountNoContentException e) {
            logger.error(e.getMessage());
        }

        return studyMapper.toListDTO(studies);
    }

    public PurchaseDTO payForCourse(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        PurchaseDTO purchaseDTO = null;

        try {
            purchaseDTO = purchaseMapper.toDTO(studentService.payCourse(student, studyGroup));
        } catch (CourseNotPaidException | UserAccountNoContentException | StudyGroupNoContentException | PurchaseNoContentException | NoSuchUserAccountException | NoSuchStudyGroupException e) {
            logger.error(e.getMessage());
        }

        return purchaseDTO;
    }

    public void rateCourse(CourseDTO courseDTO, Integer rateValue) {
        Course course = courseMapper.toEntity(courseDTO);
        try {
            studentService.rateCourse(course, rateValue);
        } catch (NoSuchCourseException | CourseNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void returnCourse(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studentService.returnCourse(student, studyGroup);
        } catch (NoSuchStudyException | StudyNoContentException | StudyGroupNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public List<PurchaseDTO> findPurchaseByStudent(UserAccountDTO userAccountDTO) {
        Student student = studentMapper.toEntity(userAccountDTO);
        return purchaseMapper.toListDTO(studentService.findPurchaseByStudent(student));
    }

    public void joinStudyGroup(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studentService.joinStudyGroup(student, studyGroup);
        } catch (PurchaseNoContentException | CourseNotPaidException | StudyGroupNoContentException | UserAccountNoContentException e) {
            logger.error(e.getMessage());
        }
    }

    public void leaveStudyGroup(UserAccountDTO studentDTO, StudyGroupDTO studyGroupDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        StudyGroup studyGroup = studyGroupMapper.toEntity(studyGroupDTO);
        try {
            studentService.leaveStudyGroup(student, studyGroup);
        } catch (NoSuchStudyException | StudyNoContentException | StudyGroupNoContentException e) {
            logger.error(e.getMessage());
        }
    }

}
