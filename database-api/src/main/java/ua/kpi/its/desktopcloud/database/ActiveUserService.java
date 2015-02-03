package ua.kpi.its.desktopcloud.database;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.kpi.its.desktopcloud.database.utils.HibernateUtils;
import ua.kpi.its.desktopcloud.database.utils.StringUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 07.04.2014
 * Time: 16:22
 */
public class ActiveUserService {
//    public static final String URL = "jdbc:mysql://localhost:3306/desktop_cloud";

//    public static final String SP_INSERT_INTO_ACTIVE_USERS = "{call sp_insert_into_activeusers(?,?)}";
    private static final String SP_INSERT_INTO_ACTIVE_USERS = "call sp_insert_into_activeusers(:p_username, :p_password)";

    private static final String SP_INSERT_INTO_RADCHECK = "{call sp_insert_into_radcheck(?,?,?,?)}";
//    private static final String SP_INSERT_INTO_RADCHECK = "select sp_insert_into_radcheck(:p_username, :p_attribute, :p_op, :p_value)";


   /* public static int allowAccess(String name, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(URL, "admin", "root");

        CallableStatement callableStatement = connection.prepareCall(SP_INSERT_INTO_ACTIVE_USERS);
        callableStatement.setString("p_username" name);
        callableStatement.setString("p_password", password);

        return callableStatement.executeUpdate();
    }*/

    public static void storeActiveUser(String name, String password) throws IOException, SQLException {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(SP_INSERT_INTO_ACTIVE_USERS)
                .setParameter("p_username", name)
                .setParameter("p_password", password);
        query.executeUpdate();
//        session.flush();
        transaction.commit();
        session.close();
    }

    public void removeActiveUser(){

    }

    public static void storeSupplicant(String name, String password) throws IOException, SQLException {
        CallableStatement statement= HibernateUtils.getConnection().prepareCall(SP_INSERT_INTO_RADCHECK);
        statement.setString(1, name);
        statement.setString(2, StringUtils.CLEAR_TEXT_PASSWORD_ATTRIBUTE);
        statement.setString(3, StringUtils.EQUALS_SIGN);
        statement.setString(4, password);
        statement.execute();
        statement.close();


//        Session session = createSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createSQLQuery(SP_INSERT_INTO_RADCHECK)
//                .setParameter("p_username", name)
//                .setParameter("p_attribute", CLEAR_TEXT_PASSWORD_ATTRIBUTE)
//                .setParameter("p_op", EQUALS_SIGN)
//                .setParameter("p_value", password);
//        query.executeUpdate();
//        session.flush();
//        transaction.commit();
//        session.close();
    }

    private static Session createSession() throws IOException, SQLException {
        return HibernateUtils.getSessionFactory().openSession();
    }

}
