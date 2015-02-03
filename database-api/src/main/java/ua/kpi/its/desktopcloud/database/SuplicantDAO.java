package ua.kpi.its.desktopcloud.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: stsypanov
 * Date: 24.03.14
 * Time: 16:14
 */


public class SuplicantDAO {
    private SessionFactory SESSION_FACTORY;

    public SuplicantDAO() throws IOException {
        SESSION_FACTORY = HibernateUtils.getSessionFactory();
    }

    public void save(Suplicant suplicant) throws Exception {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(suplicant);
        closeSession(session);
    }

    public void delete(Suplicant suplicant) throws Exception {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(suplicant);
        closeSession(session);
    }

    public boolean checkIfPresent(String aid) throws Exception {
        SessionFactory sessionFactory = SESSION_FACTORY;
        Session session = sessionFactory.openSession();
//        session.createFilter();

        return false;
    }

    private void closeSession(Session session) throws Exception {
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }


}
