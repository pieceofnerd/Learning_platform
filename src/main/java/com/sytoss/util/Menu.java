package com.sytoss.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sytoss.util.MenuUtils.scanInt;

@Service
@RequiredArgsConstructor
public class Menu {

    private final StudyMenu studyMenu;
    private final StudyGroupMenu studyGroupMenu;
    private final PurchaseMenu purchaseMenu;
    private final LessonMenu lessonMenu;
    private final CourseMenu courseMenu;


    public void start() throws Exception {
        while (true) {
            MenuUtils.printMenu(
                    "What do you want to see?",
                    "-1. Quit",
                    "1. Go to Study menu",
                    "2. Go to StudyGroup menu",
                    "3. Go to Purchase menu",
                    "4. Go to Lesson menu",
                    "5. Go to Course menu"
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
                    //StudentMenu.start();
                    break;

                case 4:
                    lessonMenu.start();
                    break;

                case 5:
                    courseMenu.start();
                    break;
            }
        }
    }
}