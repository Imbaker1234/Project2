package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

            return config.buildSessionFactory();


        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return (sessionFactory == null) ? sessionFactory = buildSessionFactory() : sessionFactory;
    }
}
