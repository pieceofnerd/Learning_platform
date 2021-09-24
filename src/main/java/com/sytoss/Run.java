package com.sytoss;

import com.sytoss.config.Config;
import com.sytoss.controller.StudyController;
import com.sytoss.mapper.CourseMapper;
import com.sytoss.mapper.StudyMapper;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.service.StudyService;
import com.sytoss.service.UserAccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Run {

    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String exit = "";
//        do {
//            showMenu();
//            choiceBetweenServices(scanner.nextInt(),scanner.nextInt());
//            System.out.println("To exit enter -> ex , to continue enter any symbol.");
//            exit = scanner.next();
//        } while (!exit.equals("ex"));
        final StudyController bean = context.getBean(StudyController.class);
        final CourseRepository courseRepository = context.getBean(CourseRepository.class);
        final StudyService studyService = context.getBean(StudyService.class);
        final StudyMapper studyMapper = context.getBean(StudyMapper.class);
        final CourseMapper courseMapper = context.getBean(CourseMapper.class);
        final UserAccountService userService = context.getBean(UserAccountService.class);
       // final Student student = (Student) userService.findUserAccountById(1L);


        final StudyGroupRepository studyGroupRepository = context.getBean(StudyGroupRepository.class);
      //  studyService.updateAssessment(userService.findUserAccountById(1L),studyGroupRepository.findOne(2L));



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



    private static StudyService getStudyService() {
        return context.getBean(StudyService.class);
    }

    private static UserAccountService getUserAccountService() {
        return context.getBean(UserAccountService.class);
    }
}