package com.sytoss.util;

import com.sytoss.controller.StudentController;
import com.sytoss.controller.UserAccountController;
import com.sytoss.exception.no_contet_exception.FeedbackNoContentException;
import com.sytoss.exception.no_such_exception.NoSuchHomeworkException;
import com.sytoss.mapper.*;
import com.sytoss.model.Lookup;
import com.sytoss.model.course.Lesson;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Mentor;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.LookupRepository;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.HomeworkService;
import com.sytoss.service.LessonService;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import com.sytoss.web.dto.filter.Filter;
import com.sytoss.web.dto.filter.FilterLessonDTO;
import com.sytoss.web.dto.save.CommunicationSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
public class MentorMenu {

    private final StudentController studentController;
    private final LessonService lessonService;
    private final UserAccountController userAccountController;
    private final UserAccountRepository userAccountRepository;
    private final HomeworkService homeworkService;
    private final LookupRepository lookupRepository;
    private final CourseRepository courseRepository;
    private final StudyRepository studyRepository;
    private final HomeworkRepository homeworkRepository;
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final FeedbackMapper feedbackMapper;
    private final HomeworkMapper homeworkMapper;
    private final StudyGroupMapper studyGroupMapper;
    private final UserAccountMapper userAccountMapper;
    private final CourseMapper courseMapper;

    @Transactional
    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Find all lesson of the mentor",
                "8. Communication functions"
        );

        long mentorId;
        long studyGroupId;
        Mentor mentor;
        Student student;
        StudyGroup studyGroup;
        UserAccountDTO userAccountDTO;
        StudyGroupDTO studyGroupDTO;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                printMentors();
                mentorId = scanInt("Write mentor id - ");

                FilterLessonDTO filter = new FilterLessonDTO();
                filter.setFilter(Filter.MENTOR);
                filter.setMentorId(mentorId);
                for (Lesson lesson :
                        lessonService.findLessonsByFilter(filter)) {
                    printClassName(lesson.getClass().getSimpleName());
                    printField("Id", lesson.getId());
                    printField("Home task", lesson.getHomeTask().getId());
                    printField("Study group", lesson.getStudyGroup().getId());
                    printField("Lesson date", lesson.getLessonDate());
                }
                break;
            case 8:
                printMentors();
                mentorId = scanInt("Write mentor id - ");
                mentor = (Mentor) userAccountRepository.findOne(mentorId);
                printMenu(
                        "1. Leave feedback",
                        "2. Leave message"
                );
                printMentorCommunicationMenu(scanInt("Write 1 or 2 - "), mentor);
                break;

        }
    }

    private void printMentorCommunicationMenu(int i, UserAccount mentor) {
        Lookup homeworkState;
        Homework homework;
        long homeworkId;
        switch (i) {
            case 1:
                homeworkState = lookupRepository.findOne(15L);
                for (Lesson lesson :
                        lessonRepository.findLessonByMentorAndActiveIsTrue(mentor)) {
                    for (Homework hw :
                            homeworkRepository.findAllByHomeTaskAndHomeworkState(lesson.getHomeTask(), homeworkState)) {
                        printClassName(hw.getClass().getSimpleName());
                        printField("id", hw.getId());
                        printField("author", hw.getAuthor().getId());
                        printField("homework state", hw.getHomeworkState().getValue());
                        printField("Home task", hw.getHomeTask().getId());
                    }
                }
                homeworkId = scanInt("Write homework id - ");
                homework = homeworkRepository.findOne(homeworkId);
                CommunicationSaveDTO feedback = new CommunicationSaveDTO();
                feedback.setSender(userAccountMapper.toDTO(mentor));
                feedback.setHomework(homeworkMapper.toDTO(homework));
                feedback.setContent(scanLine("Write content - "));
                feedback.setScore(scanInt("Write score 1-5 - "));

                try {
                    homeworkService.leaveFeedback(feedbackMapper.toEntity(feedback));
                } catch (NoSuchHomeworkException | FeedbackNoContentException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                for (Lesson lesson :
                        lessonRepository.findLessonByMentorAndActiveIsTrue(mentor)) {
                    for (Homework hw :
                            homeworkRepository.findAllByHomeTaskAndActiveIsTrue(lesson.getHomeTask())) {
                        printClassName(hw.getClass().getSimpleName());
                        printField("id", hw.getId());
                        printField("author", hw.getAuthor().getId());
                        printField("homework state", hw.getHomeworkState().getValue());
                        printField("Home task", hw.getHomeTask().getId());
                    }
                    homeworkId = scanInt("Write homework id to leave message - ");
                    homework = homeworkRepository.findOne(homeworkId);

                    CommunicationSaveDTO message = new CommunicationSaveDTO();
                    message.setSender(userAccountMapper.toDTO(mentor));
                    message.setReceiver(userAccountMapper.toDTO(homework.getHomeTask().getLesson().getMentor()));
                    message.setHomework(homeworkMapper.toDTO(homework));
                    message.setContent(scanLine("Write message content - "));
                    userAccountController.leaveMessage(message);
                    break;
                }
        }

    }

    private void printMentors() {
        List<UserAccount> users = userAccountRepository.findUserAccountsByDeletedIsFalseAndRole(2L);
        for (UserAccount user : users) {
            printClassName(user.getClass().getSimpleName());
            printField("Id", user.getId());
            printField("Full name", user.getFirstName() + " " + user.getSecondName());
            printField("Last activity", user.getLastActivity());
        }
    }
}
