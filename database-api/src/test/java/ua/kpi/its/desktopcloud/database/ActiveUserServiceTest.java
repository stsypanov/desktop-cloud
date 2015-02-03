package ua.kpi.its.desktopcloud.database;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;
import ua.kpi.its.desktopcloud.database.utils.HibernateUtils;
import ua.kpi.its.desktopcloud.database.utils.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 10.04.2014
 * Time: 14:58
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActiveUserServiceTest {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n------------");
    }

    @Test
    public void test1() throws Exception {
        testConnection();
    }

    @Test
    public void test2() throws Exception {
        storeActiveUser();
    }

    @Test
    public void test3() throws Exception {
        storeSupplicant();
    }

    @Test
    public void test4() throws Exception {
        testSelectAll();
    }

    private void testConnection() {
        boolean connected = sessionFactory.openSession().isConnected();

        if (connected){
            System.out.println("DB is connected");
        } else {
            System.out.println("DB is not connected");
        }
        System.out.println("\n------------");
    }

    private void storeActiveUser() throws IOException, SQLException {
        String name = StringUtils.generateRandomString();
        String password = StringUtils.generateRandomString();
        ActiveUserService.storeActiveUser(name, password);
        System.out.println("Supplicant with name = " + name + " and passwrod = " + password +
                " inserted into table desktop_cloud.activeusers");
        System.out.println("\n------------");
    }

    private void storeSupplicant() throws IOException, SQLException {
        String name = StringUtils.generateRandomString();
        String password = StringUtils.generateRandomString();
        ActiveUserService.storeSupplicant(name, password);
        System.out.println("Supplicant with name = " + name + " and passwrod = " + password +
                " inserted into table desktop_cloud.radcheck");
        System.out.println("\n------------");
    }

    @SuppressWarnings("unchecked")
    private void testSelectAll() throws Exception {
        SQLQuery sqlQuery = sessionFactory.openSession().createSQLQuery("select * from desktop_cloud.radcheck;");
        List list =  sqlQuery.list();
        for (Supplicant supplicant : (List<Supplicant>) list){
            System.out.println(supplicant);
        }
        System.out.println("\n------------");

        sqlQuery = sessionFactory.openSession().createSQLQuery("select * from desktop_cloud.activeusers;");
        list = sqlQuery.list();
        for (ActiveUser activeUser : (List<ActiveUser>) list){
            System.out.println(activeUser);
        }
        System.out.println("\n------------");
    }
}
