package com.sytoss.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sytoss.util.MenuUtils.scanInt;

@Service
@RequiredArgsConstructor
public class Menu {

    private final StudyMenu studyMenu;

    private final StudyGroupMenu studyGroupMenu;

    private final StudentMenu studentMenu;

    private final LessonMenu lessonMenu;

    private final UserAccountMenu userAccountMenu;

    private final MentorMenu mentorMenu;

    private final CourseMenu courseMenu;


    public void start() throws Exception {
        while (true) {
            MenuUtils.printMenu(
                    "What do you want to see?",
                    "-1. Quit",
                    "1. Go to Study menu",
                    "2. Go to StudyGroup menu",
                    "3. Go to UserAccount menu",
                    "4. Go to Student menu",
                    "5. Go to Mentor menu",
                    "6. Go to Lesson menu",
                    "7. Go to Course menu"
            );
            switch (scanInt()) {
                case -1:
                    return;
                case 1:
                    studyMenu.start();
                    break;
                case 2:
                    studyGroupMenu.start();
                    break;
                case 3:
                    userAccountMenu.start();
                    break;
                case 4:
                    studentMenu.start();
                    break;
                case 5:
                    mentorMenu.start();
                    break;
                case 6:
                    lessonMenu.start();
                    break;
                case 7:
                    courseMenu.start();
                    break;
            }
        }
    }
}