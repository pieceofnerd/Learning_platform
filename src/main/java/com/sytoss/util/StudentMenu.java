package com.sytoss.util;

import com.sytoss.controller.StudentController;
import com.sytoss.controller.UserAccountController;
import com.sytoss.mapper.*;
import com.sytoss.model.course.Course;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.UserAccountService;
import com.sytoss.web.dto.PurchaseDTO;
import com.sytoss.web.dto.StudyDTO;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.FilterUserAccountDTO;
import com.sytoss.web.dto.save.CommunicationSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class StudentMenu {

    private final StudentController studentController;
    private final UserAccountController userAccountController;
    private final UserAccountRepository userAccountRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final CourseRepository courseRepository;
    private final StudyRepository studyRepository;
    private final HomeworkRepository homeworkRepository;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final HomeworkMapper homeworkMapper;
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
                "7. Find all studies by student",
                "8. Communication functions"
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
            case 8:
                printStudents();
                studentId = scanInt("Write student id - ");
                student = (Student) userAccountRepository.findOne(studentId);
                printMenu("1. Leave comment", "2. Leave message");
                printStudentCommunicationMenu(scanInt("Write 1 or 2 - "), student);
                break;
        }
    }

    private void printStudentCommunicationMenu(int i, UserAccount student) {

        switch (i) {
            case 1:
                for (Study study :
                        studyRepository.findStudiesByDeletedIsFalseAndStudent(student)) {
                    for (Lesson lesson :
                            lessonRepository.findLessonsByActiveTrueAndStudyGroup(study.getStudyGroup())) {
                        printClassName(lesson.getClass().getSimpleName());
                        printField("id", lesson.getId());
                        printField("mentor", lesson.getMentor().getId());
                        printField("home task", lesson.getHomeTask().getId());
                    }
                }
                long lessonId = scanInt("Write lesson id to leave comment - ");
                Lesson lesson = lessonRepository.findById(lessonId);
                CommunicationSaveDTO comment = new CommunicationSaveDTO();
                comment.setSender(userAccountMapper.toDTO(student));
                comment.setLesson(lessonMapper.toDTO(lesson));
                comment.setContent(scanLine("Write comment content - "));
                userAccountController.leaveComment(comment);
                break;
            case 2:
                for (Homework homework :
                        homeworkRepository.findAllByAuthorAndActiveIsTrue(student)) {
                    printClassName(homework.getClass().getSimpleName());
                    printField("id", homework.getId());
                    printField("author", homework.getAuthor().getId());
                    printField("homework state", homework.getHomeworkState().getValue());
                    printField("Home task", homework.getHomeTask().getId());
                }
                long homeworkId = scanInt("Write homework id to leave message - ");
                Homework homework = homeworkRepository.findOne(homeworkId);

                CommunicationSaveDTO message = new CommunicationSaveDTO();
                message.setSender(userAccountMapper.toDTO(homework.getAuthor()));
                message.setReceiver(userAccountMapper.toDTO(homework.getHomeTask().getLesson().getMentor()));
                message.setHomework(homeworkMapper.toDTO(homework));
                message.setContent(scanLine("Write message content - "));
                userAccountController.leaveMessage(message);
                break;
        }
    }

    private void printStudents() {
        List<UserAccount> users = userAccountRepository.findUserAccountsByDeletedIsFalseAndRole(3L);
        for (UserAccount user : users) {
            printClassName(user.getClass().getSimpleName());
            printField("Id", user.getId());
            printField("Full name", user.getFirstName() + " " + user.getSecondName());
            printField("Last activity", user.getLastActivity());
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
