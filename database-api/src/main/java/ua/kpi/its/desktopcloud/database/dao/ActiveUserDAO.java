package ua.kpi.its.desktopcloud.database.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.utils.HibernateUtils;

import java.io.IOException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 24.03.14
 * Time: 16:14
 */


public class ActiveUserDAO {
    private SessionFactory sessionFactory;

    public ActiveUserDAO() throws IOException {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void save(ActiveUser activeUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(activeUser);
        commitAndClose(session);
    }

    public ActiveUser save(String username, String password) throws HibernateException{
        ActiveUser user = new ActiveUser(username, password);
        save(user);
        return user;
    }

    public void delete(ActiveUser activeUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(activeUser);
        commitAndClose(session);
    }

    public boolean checkIfPresent(String aid) {
        Session session = sessionFactory.openSession();
//        session.createFilter();

        return false;
    }

    private void commitAndClose(Session session) {
        session.getTransaction().commit();
        if (session != null) session.close();
    }

    @SuppressWarnings("unchecked")
    public List<ActiveUser> getAll() {
        Session session = sessionFactory.openSession();
        return (List<ActiveUser>) session.createCriteria(ActiveUser.class).list();
    }

    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from ActiveUser").executeUpdate();
        commitAndClose(session);
    }


}
