package ua.kpi.its.desktopcloud.database;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ua.kpi.its.desktopcloud.database.dao.ActiveUserDAO;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.utils.StringUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 12:22
 */
public class TestActiveUserDAO extends TestCase {
    private static ActiveUserDAO dao;
    private static ActiveUser user;

    public static Test suite() {
        return new TestSetup(new TestSuite(TestActiveUserDAO.class)) {

            protected void setUp() throws Exception {
                dao = new ActiveUserDAO();
                String name = StringUtils.generateRandomString();
                String password = StringUtils.generateRandomString();
                user = new ActiveUser(name, password);
            }

            protected void tearDown() throws Exception {
                listAll();
            }
        };
    }


    public void testSave() throws Exception {
        dao.save(user);
        System.out.println("Active user with name = " + user.getUserName() + " and passwrod = " + user.getPassword() +
                " stored in table desktop_cloud.activeusers");
        System.out.println("\n------------");
    }

    public void testDelete() throws Exception {
        dao.delete(user);
        System.out.println("Supplicant with name = " + user.getUserName() + " and passwrod = " + user.getPassword() +
                " deleted from table desktop_cloud.activeusers");
        System.out.println("\n------------");
    }

    public void testDeleteAll() throws Exception {
        dao.save(new ActiveUser(StringUtils.generateRandomString(), StringUtils.generateRandomString()));
        dao.save(new ActiveUser(StringUtils.generateRandomString(), StringUtils.generateRandomString()));
        dao.save(new ActiveUser(StringUtils.generateRandomString(), StringUtils.generateRandomString()));

        listAll();

        delete();

        listAll();
    }

    public static void listAll() {
        List<ActiveUser> list = dao.getAll();
        if (!list.isEmpty()) {
            for (ActiveUser user : list) {
                System.out.println(user);
            }
        } else {
            System.out.println("There are no active users in database");
        }
        System.out.println("\n------------");
    }

    private void delete() {
        dao.deleteAll();
        System.out.println("All active users deleted from database\n------------");
    }
}
