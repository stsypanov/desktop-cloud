package ua.kpi.its.desktopcloud.database;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ua.kpi.its.desktopcloud.database.dao.SupplicantDAO;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;
import ua.kpi.its.desktopcloud.database.utils.StringUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 15:00
 */
public class TestSupplicantDAO extends TestCase {
    private static SupplicantDAO dao;
    private static Supplicant supplicant;

    public static Test suite() {
        return new TestSetup(new TestSuite(TestSupplicantDAO.class)) {

            protected void setUp() throws Exception {
                dao = new SupplicantDAO();
                String name = StringUtils.generateRandomString();
                String password = StringUtils.generateRandomString();
                supplicant = new Supplicant(name, StringUtils.CLEAR_TEXT_PASSWORD_ATTRIBUTE, StringUtils.EQUALS_SIGN, password);
            }

            protected void tearDown() throws Exception {
                List<Supplicant> list = dao.getAll();
                for (Supplicant user : list){
                    System.out.println(user);
                }
                System.out.println("\n------------");
            }
        };
    }

    public void testSave() throws Exception {
        dao.save(supplicant);
        System.out.println();
        System.out.println("\n------------");
    }
}
