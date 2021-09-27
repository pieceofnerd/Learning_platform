package com.sytoss;

import com.sytoss.config.Config;
import com.sytoss.controller.StudentController;
import com.sytoss.mapper.StudentMapper;
import com.sytoss.mapper.StudyGroupMapper;
import com.sytoss.mapper.UserAccountMapper;
import com.sytoss.model.course.StudyGroup;
import com.sytoss.model.education.UserAccount;
import com.sytoss.model.education.user.Student;
import com.sytoss.repository.course.CourseRepository;
import com.sytoss.repository.course.StudyGroupRepository;
import com.sytoss.repository.education.UserAccountRepository;
import com.sytoss.service.StudentService;
import com.sytoss.service.StudyService;
import com.sytoss.util.Menu;
import com.sytoss.web.dto.StudyGroupDTO;
import com.sytoss.web.dto.UserAccountDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Run {

    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static void main(String[] args) throws Exception {

        Menu menu = context.getBean(Menu.class);
        menu.start();

//
//        StudyGroupRepository studyGroupRepository = context.getBean(StudyGroupRepository.class);
//        UserAccountRepository userAccountRepository = context.getBean(UserAccountRepository.class);
//        UserAccountMapper userAccountMapper = context.getBean(UserAccountMapper.class);
//        StudentMapper studentMapper = context.getBean(StudentMapper.class);
//        StudentService studentService = context.getBean(StudentService.class);
//        StudentController studentController = context.getBean(StudentController.class);
//        StudyGroupMapper studyGroupMapper = context.getBean(StudyGroupMapper.class);
//        StudyService studyService = context.getBean(StudyService.class);
//        CourseRepository courseRepository = context.getBean(CourseRepository.class);
//
//        StudyGroup studyGroup = studyGroupRepository.findById(2L);
//        StudyGroupDTO studyGroupDTO = studyGroupMapper.toDTO(studyGroup);
//        studentService.rateCourse(courseRepository.findOne(2L),5);
//        Student student = (Student) userAccountRepository.findOne(2L);
//        StudyGroup studyGroup = studyGroupRepository.findById(8L);
//        UserAccountDTO userAccountDTO = studentMapper.toDTO(student);
//        UserAccount s = user;
//        System.out.println(user);
//        UserAccountDTO userAccountDTO = userAccountMapper.toDTO(user);
//        System.out.println();
//        System.out.println();
//        System.out.println(userAccountDTO);
//        studentService.payCourse(user, studyGroup);

    }
}