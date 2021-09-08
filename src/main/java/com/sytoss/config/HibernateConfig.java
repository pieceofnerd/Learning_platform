package com.sytoss.config;

import com.sytoss.model.Lookup;
import com.sytoss.model.LookupName;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.*;
import com.sytoss.model.education.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Communication.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(HomeTask.class)
                .addAnnotatedClass(Homework.class)
                .addAnnotatedClass(Lesson.class)
                .addAnnotatedClass(LessonTemplate.class)
                .addAnnotatedClass(Price.class)
                .addAnnotatedClass(Promotion.class)
                .addAnnotatedClass(StudyGroup.class)
                .addAnnotatedClass(Topic.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(Study.class)
                .addAnnotatedClass(UserAccount.class)
                .addAnnotatedClass(Lookup.class)
                .addAnnotatedClass(LookupName.class)
                .addAnnotatedClass(Media.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}