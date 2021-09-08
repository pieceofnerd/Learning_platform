package com.sytoss;

import com.sytoss.config.HibernateConfig;
import com.sytoss.model.Lookup;
import com.sytoss.model.LookupName;
import com.sytoss.model.Media;
import com.sytoss.model.communication.Communication;
import com.sytoss.model.course.*;
import com.sytoss.model.education.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Run {
    public static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
//        sessionFactory = new Configuration()
//                .addAnnotatedClass(Communication.class)
//                .addAnnotatedClass(Category.class)
//                .addAnnotatedClass(Course.class)
//                .addAnnotatedClass(HomeTask.class)
//                .addAnnotatedClass(Homework.class)
//                .addAnnotatedClass(Lesson.class)
//                .addAnnotatedClass(LessonTemplate.class)
//                .addAnnotatedClass(Price.class)
//                .addAnnotatedClass(Promotion.class)
//                .addAnnotatedClass(StudyGroup.class)
//                .addAnnotatedClass(Topic.class)
//                .addAnnotatedClass(Address.class)
//                .addAnnotatedClass(Purchase.class)
//                .addAnnotatedClass(Study.class)
//                .addAnnotatedClass(UserAccount.class)
//                .addAnnotatedClass(Lookup.class)
//                .addAnnotatedClass(LookupName.class)
//                .addAnnotatedClass(Media.class)
//                .buildSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Homework homework = session.find(Homework.class, 1L);
        System.out.println(homework.getFeedbacks());
        session.getTransaction().commit();
        session.close();

    }
}