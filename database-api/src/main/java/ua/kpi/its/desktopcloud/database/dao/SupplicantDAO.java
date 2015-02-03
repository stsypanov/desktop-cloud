package ua.kpi.its.desktopcloud.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.kpi.its.desktopcloud.database.utils.HibernateUtils;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 14:23
 */
public class SupplicantDAO {
    private SessionFactory sessionFactory;

    public SupplicantDAO() throws IOException {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void save(Supplicant supplicant) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(supplicant);
        commitAndClose(session);
    }

    public void delete(Supplicant supplicant) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(session);
        commitAndClose(session);
    }

    @SuppressWarnings("unchecked")
    public List<Supplicant> getAll() {
        Session session = sessionFactory.openSession();
        return (List<Supplicant>) session.createCriteria(Supplicant.class).list();
    }

    private void commitAndClose(Session session) {
        session.getTransaction().commit();
        if (session != null) session.close();
    }


}
