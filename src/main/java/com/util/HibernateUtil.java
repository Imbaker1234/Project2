package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {

        try {

            //Create the SessionFactory using the hibernate.cfg.xml
            Configuration config = new Configuration();
            //Provide hibernate's Session Factory with a cfg.
            config.configure("hibernate.cfg.xml"); //This string
            // argument is not required if you kept the default
            // config file name.

            assignAnnotations(config);

            return config.buildSessionFactory();


        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return (sessionFactory == null) ? sessionFactory = buildSessionFactory() : sessionFactory;
    }

    private static void assignAnnotations(Configuration config) {
        Reflections reflections = new Reflections("com");

        Set<Class<? extends Object>> allClasses =
                reflections.getTypesAnnotatedWith(Entity.class);

        for (Class c : allClasses) {
            config.addAnnotatedClass(c);
        }
    }
}
