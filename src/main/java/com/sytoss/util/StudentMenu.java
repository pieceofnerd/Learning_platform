package com.sytoss.util;

import com.sytoss.controller.StudentController;
import com.sytoss.controller.UserAccountController;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Purchase;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.StudentService;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterPurchaseDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class StudentMenu {

    //    private final StudentService studentService;
    private final StudentController studentController;
    private final UserAccountRepository userAccountRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final CourseRepository courseRepository;
    private final PurchaseMapper purchaseMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final UserAccountMapper userAccountMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Pay course",
                "2. Rate course"
        );

        long studentId;
        long studyGroupId;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                studentId = scanInt("Write student id - ");
                studyGroupId = scanInt("Write study group id - ");
                Student student = (Student) userAccountRepository.findOne(studentId);
                StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId);
                UserAccountDTO userAccountDTO = userAccountMapper.toDTO(student);
                StudyGroupDTO studyGroupDTO = studyGroupMapper.toDTO(studyGroup);

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

                studentController.rateCourse(courseMapper.toDTO(course),rate);
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
