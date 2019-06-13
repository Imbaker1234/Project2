package com.util;

import com.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {


    public static void main(String[] args) {

        /*
        THE HIBERNATE TRIALS
         */

        //        Comment c = new Comment(210, 18, "This piece isn't appropriate for a children's gallery!");
//        Showing s = new Showing("3821 USF Holly Drive, Tampa, FL 33620", "1560897000000", 1, "You'll love seeing " +
//                "Rodan's early works at this celebrated children's museum!");
//        User u = new User("HTafty", "2Big4Bathtime", "Howard", "Taft", "BigPrez@WhiteHouse.gov", 1);
//
//
//        try (SessionFactory factory = HibernateUtil.getSessionFactory();
//             Session session = factory.getCurrentSession()) {
//            session.beginTransaction();
//            session.save(c);
//            session.save(s);
//            session.save(u);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        /*
        THE SPRING TRIALS
         */

//        //Load the Spring configuration file.
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//
//        //Retrieve a bean from the Spring Container
//        Coach coach = context.getBean("myCoach", Coach.class);
//
//        // Call getDailyWorkout on the retrieved bean
//        System.out.println(coach.getDailyWorkout());
//
//        // Call getMotivationService() on the retrieved bean
//        System.out.println(coach.getMotivation());
//
//        FootballCoach footballCoach = context.getBean("myFootballCoach", FootballCoach.class);
//
//        System.out.println(footballCoach.getDailyWorkout());
//        System.out.println(footballCoach.getMotivation());
//        System.out.println(footballCoach.getEmail());
//        System.out.println(footballCoach.getTeamName());
//
//        System.out.println(coach);
//
//        Coach assistantCoach = context.getBean("myCoach", Coach.class);
//        System.out.println(assistantCoach);
    }
}
