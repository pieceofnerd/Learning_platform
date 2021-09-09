package com.sytoss;

import com.sytoss.model.communication.Feedback;
import com.sytoss.model.education.Homework;
import com.sytoss.model.education.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Run {
    public static SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Feedback feedback = new Feedback();
        feedback.setContent("Hello test");
        feedback.setSender(session.find(UserAccount.class, 12L));
        feedback.setHomework(session.find(Homework.class, 5L));
        feedback.setScore(5);
        session.save(feedback);

        session.getTransaction().commit();
        session.close();

    }
}