package ua.kpi.its.desktopcloud.database.utils;

import ua.kpi.its.desktopcloud.utils.LogUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 31.03.14
 * Time: 11:16
 */
public class HibernateUtils {
    private static final Logger LOGGER = Logger.getLogger(HibernateUtils.class.getName());

//    private static final String MYSQL_PROPERTIES = "trash/sql-database.properties";
//    private static final String POSTGRES_PROPERTIES = "trash/postgresql-database.properties";
    private static final String DB_PROPERTIES = "db.properties";

    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "current_session_context_class";
    public static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    public static final String HIBERNATE_TRANSACTION_FACTORY_CLASS = "hibernate.transaction.factory_class";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    private static SessionFactory sessionFactory;
    private static final Properties properties = new Properties();



    public static SessionFactory getSessionFactory() throws IOException {
        if (sessionFactory == null){
            try {
                sessionFactory = createSessionFactory();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to create SessionFactory", e);
                throw e;
            }
        }
        return sessionFactory;
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty(HIBERNATE_CONNECTION_URL),
                properties.getProperty(HIBERNATE_CONNECTION_USERNAME),
                properties.getProperty(HIBERNATE_CONNECTION_PASSWORD));
    }

    private static SessionFactory createSessionFactory() throws IOException {
        LOGGER.addHandler(LogUtils.getDatabaseLogHandler());

        Configuration conf = new Configuration();
        conf.addAnnotatedClass(ActiveUser.class);
        conf.addAnnotatedClass(Supplicant.class);

        loadDatabaseProperties();

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

    private static void loadDatabaseProperties() throws IOException {
        try {
            properties.load(HibernateUtils.class.getClassLoader().getResourceAsStream(DB_PROPERTIES));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load DB connection properties from file " + DB_PROPERTIES, e);
            throw new IOException(e);
        }
    }

//    private static InputStream loadDatabseProperties(){
//        return HibernateUtils.class.getClassLoader().getResourceAsStream(DB_PROPERTIES);
//    }

//    private static InputStream loadPostgresqlProperties() {
//        return HibernateUtils.class.getClassLoader().getResourceAsStream(POSTGRES_PROPERTIES);
//    }

//    private static InputStream loadMySqlProperties() {
//        HibernateUtils.class.getClassLoader().getResourceAsStream(MYSQL_PROPERTIES);
//        return HibernateUtils.class.getClassLoader().getResourceAsStream(MYSQL_PROPERTIES);
//    }
}
