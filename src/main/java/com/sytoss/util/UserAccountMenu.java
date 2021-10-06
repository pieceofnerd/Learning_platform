package com.sytoss.util;

import com.sytoss.controller.UserAccountController;
import com.sytoss.mapper.*;
import com.sytoss.model.course.*;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.Study;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.LessonRepository;
import com.sytoss.repository.course.LessonTemplateRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.course.TopicRepository;
import com.sytoss.repository.education.HomeworkRepository;
import com.sytoss.repository.education.StudyRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.web.dto.*;
import com.sytoss.web.dto.save.AddressSaveDTO;
import com.sytoss.web.dto.save.CommunicationSaveDTO;
import com.sytoss.web.dto.save.MediaSaveDTO;
import com.sytoss.web.dto.save.UserAccountSaveDTO;
import com.sytoss.web.dto.update.UserAccountUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.sytoss.util.MenuUtils.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountMenu {
    private final UserAccountController userAccountController;
    private final UserAccountRepository userAccountRepository;
    private final LessonRepository lessonRepository;
    private final StudyRepository studyRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final TopicRepository topicRepository;
    private final LessonTemplateRepository lessonTemplateRepository;
    private final HomeworkRepository homeworkRepository;
    private final HomeworkMapper homeworkMapper;
    private final AddressMapper addressMapper;
    private final MediaMapper mediaMapper;
    private final LessonMapper lessonMapper;
    private final UserAccountMapper userAccountMapper;

    public void start() throws Exception {
        printMenu(
                "-1. Quit",
                "1. Register user",
                "2. Send CV",
                "3. Update user",
                "4. Delete user",
                "5. Communication functions"
        );

        long userId;
        UserAccount user;
        Student student;
        StudyGroup studyGroup;
        UserAccountSaveDTO userAccountSaveDTO;
        StudyGroupDTO studyGroupDTO;
        switch (scanInt()) {
            case -1:
                return;
            case 1:
                userAccountSaveDTO = new UserAccountSaveDTO();
                String fName = scanLine("Write first name - ");
                String sName = scanLine("Write second name - ");
                Date birthday = scanDate("Write birthday(optional) - ");
                String bio = scanLine("Write bio(optional) - ");
                if (scanInt("If you want to add your address(optional), enter - 1") == 1) {

                    AddressSaveDTO address = new AddressSaveDTO();
                    printClassName(address.getClass().getSimpleName());
                    address.setCountry(scanLine("Write country - "));
                    address.setRegion(scanLine("Write region - "));
                    address.setLocality(scanLine("Write locality - "));
                    address.setPostcode(scanLine("Write post code(optional) - "));
                    address.setStreetName(scanLine("Write street name - "));
                    address.setHouseNumber(scanInt("Write house number - "));
                    userAccountSaveDTO.setAddress(address);
                }
                if (scanInt("If you want to add photo(optional), enter - 1") == 1) {
                    MediaSaveDTO mediaSaveDTO = new MediaSaveDTO();
                    mediaSaveDTO.setMediaPath(scanLine("Write image path - "));
                    userAccountSaveDTO.setPhoto(mediaSaveDTO);
                }
                String email = scanLine("Write email - ");
                String password = scanLine("Write password - ");
                userAccountSaveDTO.setFirstName(fName);
                userAccountSaveDTO.setSecondName(sName);
                userAccountSaveDTO.setBio(bio);
                userAccountSaveDTO.setBirthday(birthday);
                userAccountSaveDTO.setEmail(email);
                userAccountSaveDTO.setPassword(password.toCharArray());

                userAccountController.registerUserAccount(userAccountSaveDTO);
                break;
            case 2:
                String cv = scanLine("Add your CV - ");
                MediaSaveDTO mediaSaveDTO = new MediaSaveDTO();
                mediaSaveDTO.setMediaPath(cv);

                userAccountController.saveCV(mediaSaveDTO);
                break;
            case 3:
                userId = scanInt("Write user id to update - ");
                user = userAccountRepository.findOne(userId);
                printUser(user);
                userUpdate(getUserUpdate(user));
                break;
            case 4:
                userId = scanInt("Write user id to update - ");
                user = userAccountRepository.findOne(userId);
                UserAccountUpdateDTO userUpdate = getUserUpdate(user);
                userAccountController.deleteUserAccount(userUpdate);
                break;
            case 5:
                printUserRole();
                break;
        }
    }


    private void userUpdate(UserAccountUpdateDTO userDTO) throws Exception {
        switch (scanInt("Write field which want to update - ")) {
            case 1:
                userDTO.setFirstName(scanLine("Write new first name - "));
                break;
            case 2:
                userDTO.setSecondName(scanLine("Write new second name - "));
                break;
            case 3:
                userDTO.setBirthday(scanDate("Write new birthday - "));
                break;
            case 4:
                userDTO.setBio(scanLine("Write new bio - "));
                break;
            case 5:
                printClassName(AddressDTO.class.getSimpleName());
                AddressDTO address = new AddressDTO();
                address.setCountry(scanLine("Write country - "));
                address.setRegion(scanLine("Write region - "));
                address.setLocality(scanLine("Write locality - "));
                address.setPostcode(scanLine("Write postcode(optional) -"));
                address.setStreetName(scanLine("Write street name - "));
                address.setHouseNumber(scanInt("Write houes number"));
                userDTO.setAddress(address);
                break;
            case 6:
                //TODO PHOTO
                break;
            case 7:
                userDTO.setEmail(scanLine("Writ new e-mail - "));
                break;
            case 8:
                String oldPassword = scanLine("Write your old password - ");
                String newPassword = scanLine("Write new password - ");
                userAccountController.resetPassword(userDTO, oldPassword.toCharArray(), newPassword.toCharArray());
                break;

        }
        userAccountController.updateUserAccount(userDTO);
    }

    private void printUser(UserAccount user) {
        printClassName(user.getClass().getSimpleName());
        printField("1. First name", user.getFirstName());
        printField("2. Second name", user.getSecondName());
        printField("3. Birthday", user.getBirthday());
        printField("4. Bio", user.getBio());
        printField("5. Address", user.getAddress());
        printField("6. Photo", user.getPhoto());
        printField("7. Email", user.getEmail());
        printField("8. Password", "*****");
    }

    private UserAccountUpdateDTO getUserUpdate(UserAccount user) {
        UserAccountUpdateDTO userUpdate = new UserAccountUpdateDTO();
        userUpdate.setId(user.getId());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setSecondName(user.getSecondName());
        userUpdate.setBirthday(user.getBirthday());
        userUpdate.setBio(user.getBio());
        userUpdate.setAddress(addressMapper.toDTO(user.getAddress()));
        userUpdate.setPhoto(mediaMapper.toDTO(user.getPhoto()));
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        return userUpdate;
    }



    // TODO
    private void printMentorCommunicationMenu(int i, UserAccount mentor) {
        switch (i) {
            case 1:

                break;
            case 2:

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

    private void printMentors() {
        List<UserAccount> users = userAccountRepository.findUserAccountsByDeletedIsFalseAndRole(2L);
        for (UserAccount user : users) {
            printClassName(user.getClass().getSimpleName());
            printField("Id", user.getId());
            printField("Full name", user.getFirstName() + " " + user.getSecondName());
            printField("Last activity", user.getLastActivity());
        }
    }

    private void printAdmins() {
        List<UserAccount> users = userAccountRepository.findUserAccountsByDeletedIsFalseAndRole(1L);
        for (UserAccount user : users) {
            printClassName(user.getClass().getSimpleName());
            printField("Id", user.getId());
            printField("Full name", user.getFirstName() + " " + user.getSecondName());
            printField("Last activity", user.getLastActivity());
        }
    }

    private List<UserAccount> printUserRole() {
        List<UserAccount> users = null;
        UserAccount user;
        long userId;
        printMenu(
                "1. Students",
                "2. Mentors",
                "3. Admins"
        );
        switch (scanInt("Write 1,2 or 3 - ")) {
            case 1:
                printStudents();
                userId = scanInt("Write student id - ");
                user = userAccountRepository.findOne(userId);
                printMenu(
                        "1. Comment",
                        "2. Message"
                );
                printStudentCommunicationMenu(scanInt("Write 1 or 2 - "), user);
                break;
            case 2:
                //TODO
                printMentors();
                userId = scanInt("Write mentor id - ");
                user = userAccountRepository.findOne(userId);
                printMenu(
                        "1. Feedback",
                        "2. Message"
                );
                break;

            case 3:
                printAdmins();
                break;
        }
        return users;
    }
}
