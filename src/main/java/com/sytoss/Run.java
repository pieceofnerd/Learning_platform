package com.sytoss;

import com.sytoss.config.Config;

import com.sytoss.model.education.Study;
import com.sytoss.service.CourseService;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import com.sytoss.service.impl.CourseServiceImpl;
import com.sytoss.web.dto.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Scanner;


public class Run {

    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String exit = "";
        do {
            showMenu();
            choiceBetweenServices(scanner.nextInt(),scanner.nextInt());
            System.out.println("To exit enter -> ex , to continue enter any symbol.");
            exit = scanner.next();
        } while (!exit.equals("ex"));
    }


    private static void showMenu() {
        System.out.println("1.User\n" +
                "\t\t1. find user by id\n" +
                "\t\t2. find all student studies" +
                "\n" +
                "2.Study\n" +
                "\t\t1. find all studies\n" +
                "\t\t2. find study by id" +
                "\n" +
                "4.Purchase\n" +
                "Input case:");
    }

    private static void choiceBetweenServices(int c, int methodNumber) throws Exception {
        Scanner sc = new Scanner(System.in);
        switch (c) {
            case 1: {
                final UserAccountService userAccountService = getUserAccountService();
                final StudyService studyService = getStudyService();
                if (methodNumber == 1) {
                    System.out.print("Enter user id - ");
                    System.out.println(userAccountService.findUserAccountById(sc.nextLong()));
                }
                if (methodNumber == 2) {
                    System.out.print("Enter user id - ");
                    FilterDTO filter = new FilterDTO();
                    filter.setStudent(sc.nextLong());
                    for (Study study :
                            studyService.findStudiesByFilter(filter)) {
                        System.out.println(study);
                    }
                }
                break;
            }
            case 2: {

                break;
            }
            default: {
                System.out.println("ERROR");
            }
        }
    }

    private static StudyService getStudyService() {
        return context.getBean(StudyService.class);
    }

    private static UserAccountService getUserAccountService() {
        return context.getBean(UserAccountService.class);
    }
}