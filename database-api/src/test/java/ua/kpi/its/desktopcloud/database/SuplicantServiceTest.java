package ua.kpi.its.desktopcloud.database;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: stsypanov
 * Date: 10.04.2014
 * Time: 14:58
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SuplicantServiceTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        sessionFactory = HibernateUtils.getSessionFactory();
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

        if (connected) {
            System.out.println("DB is connected");
        } else {
            System.out.println("DB is not connected");
        }
        System.out.println("\n------------");
    }

    private void storeActiveUser() throws IOException, SQLException {
        String name = generateRandomString();
        String password = generateRandomString();
        SuplicantService.storeActiveUser(name, password);
        System.out.println("Supplicant with name = " + name + " and passwrod = " + password +
                " inserted into table desktop_cloud.activeusers");
        System.out.println("\n------------");
    }

    private void storeSupplicant() throws IOException, SQLException {
        String name = generateRandomString();
        String password = generateRandomString();
        SuplicantService.storeSupplicant(name, password);
        System.out.println("Supplicant with name = " + name + " and passwrod = " + password +
                " inserted into table desktop_cloud.radcheck");
        System.out.println("\n------------");
    }

    private void testSelectAll() throws Exception {
        SQLQuery sqlQuery = sessionFactory.openSession().createSQLQuery("select * from desktop_cloud.radcheck;");
        List list = sqlQuery.list();
        System.out.println(list);
        System.out.println("\n------------");

        sqlQuery = sessionFactory.openSession().createSQLQuery("select * from desktop_cloud.activeusers;");
        list = sqlQuery.list();
        System.out.println(list);
        System.out.println("\n------------");
    }

    private String generateRandomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (byte i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }


    /*@Test
    public void testInsert() throws Exception {
        String name = generateRandomString();
        String password = generateRandomString();
        if (SuplicantService.allowAccess(name, password) == 1){
            System.out.println("Supplicant with name = " + name + " and passwrod = " + password + " inserted into table");
        }
    }*/

}
