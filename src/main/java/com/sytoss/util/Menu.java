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


    public void start() throws Exception {
        while (true) {
            MenuUtils.printMenu(
                    "What you want to see?",
                    "-1. Quit",
                    "1. Go to Study menu",
                    "2. Go to StudyGroup menu",
                    "3. Go to Student menu"
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
                    studentMenu.start();
                    break;
            }
        }
    }
}