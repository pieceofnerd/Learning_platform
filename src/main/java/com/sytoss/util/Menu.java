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

    private final PromotionMenu promotionMenu;


    public void start() throws Exception {
        while (true) {
            MenuUtils.printMenu(
                    "What do you want to see?",
                    "-1. Quit",
                    "1. Go to StudyGroup menu",
                    "2. Go to UserAccount menu",
                    "3. Go to Student menu",
                    "4. Go to Mentor menu",
                    "5. Go to Lesson menu",
                    "6. Go to Course menu",
                    "7. Go to Promotion menu"
            );
            switch (scanInt()) {
                case -1:
                    return;
                case 1:
                    studyGroupMenu.start();
                    break;
                case 2:
                    userAccountMenu.start();
                    break;
                case 3:
                    studentMenu.start();
                    break;
                case 4:
                    mentorMenu.start();
                    break;
                case 5:
                    mentorMenu.start();
                    break;
                case 6:
                    lessonMenu.start();
                    break;
                case 7:
                    promotionMenu.start();
                    break;
            }
        }
    }
}