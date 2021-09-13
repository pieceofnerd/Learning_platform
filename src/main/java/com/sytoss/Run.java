package com.sytoss;

import com.sytoss.config.Config;
import com.sytoss.repository.StudyRepository;
import com.sytoss.service.StudyService;
import com.sytoss.service.impl.StudyServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Run {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);

        final StudyService bean = appContext.getBean(StudyService.class);

        System.out.println(bean.findStudyById(2L));

    }
}