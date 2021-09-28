package com.sytoss.util;

import com.sytoss.controller.StudentController;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class StudentMenu {

    private final StudentController studentController;
    private final UserAccountRepository userAccountRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final CourseRepository courseRepository;
    private final StudyGroupMapper studyGroupMapper;
    private final UserAccountMapper userAccountMapper;
    private final CourseMapper courseMapper;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Pay course",
                "2. Rate course",
                "3. Join StudyGroup",
                "4. Leave StudyGroup",
                "5. Return course",
                "6. Find all purchase by student",
                "7. Find all studies by student"
        );

        long studentId;
        long studyGroupId;
        Student student;
        StudyGroup studyGroup;
        UserAccountDTO userAccountDTO;
        StudyGroupDTO studyGroupDTO;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                studyGroup = studyGroupRepository.findById(studyGroupId);
                userAccountDTO = userAccountMapper.toDTO(student);
                studyGroupDTO = studyGroupMapper.toDTO(studyGroup);

//                Student userAccount = studentMapper.toEntity(userAccountDTO);

                PurchaseDTO purchaseDTO = studentController.payForCourse(userAccountDTO, studyGroupDTO);
                printClassName(purchaseDTO.getClass().getSimpleName());
                printField("id", purchaseDTO.getId());
                printField("student", purchaseDTO.getStudent());
                printField("studyGroup", purchaseDTO.getStudyGroup());
                printField("purchaseStatus", purchaseDTO.getPurchaseStatus());
                printField("cost", purchaseDTO.getCost());
                printField("purchaseDate", purchaseDTO.getPurchaseDate());
                printField("updatedDate", purchaseDTO.getUpdatedDate());
                break;
            case 2:
                long courseId = scanInt("Write course id - ");
                int rate = scanInt("Rate course - ");

                Course course = courseRepository.findOne(courseId);

                studentController.rateCourse(courseMapper.toDTO(course), rate);
                break;
            case 3:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                studyGroup = studyGroupRepository.findById(studyGroupId);
                userAccountDTO = userAccountMapper.toDTO(student);
                studyGroupDTO = studyGroupMapper.toDTO(studyGroup);

                studentController.joinStudyGroup(userAccountDTO, studyGroupDTO);
                break;
            case 4:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                studyGroup = studyGroupRepository.findById(studyGroupId);
                userAccountDTO = userAccountMapper.toDTO(student);
                studyGroupDTO = studyGroupMapper.toDTO(studyGroup);

                studentController.leaveStudyGroup(userAccountDTO, studyGroupDTO);
                break;
            case 5:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                studyGroup = studyGroupRepository.findById(studyGroupId);
                userAccountDTO = userAccountMapper.toDTO(student);
                studyGroupDTO = studyGroupMapper.toDTO(studyGroup);

                studentController.returnCourse(userAccountDTO, studyGroupDTO);
                break;
            case 6:
                studentId = scanInt("Write student id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                userAccountDTO = userAccountMapper.toDTO(student);
                for (PurchaseDTO p : studentController.findPurchaseByStudent(userAccountDTO)) {
                    printClassName(p.getClass().getSimpleName());
                    printField("id", p.getId());
                    printField("student", p.getStudent().getId());
                    printField("study group", p.getStudyGroup().getId());
                    printField("cost", p.getCost());
                    printField("purchase date", p.getPurchaseDate());
                    printField("purchase status", p.getPurchaseStatus().getValue());
                    printField("updated date", p.getUpdatedDate());
                }
                break;
            case 7:
                studentId = scanInt("Write student id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                userAccountDTO = userAccountMapper.toDTO(student);
                for (StudyDTO s : studentController.findStudiesByStudent(userAccountDTO)) {
                    printClassName(s.getClass().getSimpleName());
                    printField("id", s.getId());
                    printField("student", s.getStudent().getId());
                    printField("study group", s.getStudyGroup().getId());
                    printField("progress", s.getProgress());
                    printField("assessment", s.getAssessment());
                    printField("deleted", s.isDeleted());
                    printField("createdDate", s.getCreatedDate());
                    printField("updatedDate", s.getUpdatedDate());
                }
                break;
        }
    }

    private FilterUserAccountDTO selectFilter(int i) throws Exception {
        FilterUserAccountDTO filter = new FilterUserAccountDTO();
        switch (i) {
            case 1:

                break;
            default:
                throw new Exception("FILTER ERROR");
        }
        return filter;
    }
}
