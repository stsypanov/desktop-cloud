package ua.kpi.its.desktopcloud.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: stsypanov
 * Date: 31.03.14
 * Time: 11:16
 */
public class HibernateUtils {
    private static final String PROPERTIES_FILE_NAME = "database.properties";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "current_session_context_class";
    public static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    public static final String HIBERNATE_TRANSACTION_FACTORY_CLASS = "hibernate.transaction.factory_class";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() throws IOException {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() throws IOException {
        Properties properties = new Properties();
        HibernateUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
        InputStream is = HibernateUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
        properties.load(is);

        Configuration conf = new Configuration();
        conf.addAnnotatedClass(Suplicant.class);

//        conf.setProperty("hibernate.default_schema", "APP");
        conf.setProperty(HIBERNATE_DIALECT, properties.getProperty(HIBERNATE_DIALECT));
        conf.setProperty(HIBERNATE_CONNECTION_URL, properties.getProperty(HIBERNATE_CONNECTION_URL));
        conf.setProperty(HIBERNATE_CONNECTION_USERNAME, properties.getProperty(HIBERNATE_CONNECTION_USERNAME));
        conf.setProperty(HIBERNATE_CONNECTION_PASSWORD, properties.getProperty(HIBERNATE_CONNECTION_PASSWORD));
        conf.setProperty(CURRENT_SESSION_CONTEXT_CLASS, properties.getProperty(CURRENT_SESSION_CONTEXT_CLASS));
        conf.setProperty(HIBERNATE_CONNECTION_DRIVER_CLASS, properties.getProperty(HIBERNATE_CONNECTION_DRIVER_CLASS));
        conf.setProperty(HIBERNATE_TRANSACTION_FACTORY_CLASS, properties.getProperty(HIBERNATE_TRANSACTION_FACTORY_CLASS));
        conf.setProperty(HIBERNATE_SHOW_SQL, properties.getProperty(HIBERNATE_SHOW_SQL));

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(conf.getProperties());
        sessionFactory = conf.buildSessionFactory(serviceRegistryBuilder.build());

        return sessionFactory;
    }
}
