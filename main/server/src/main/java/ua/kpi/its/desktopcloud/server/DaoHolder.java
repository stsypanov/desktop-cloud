package ua.kpi.its.desktopcloud.server;

import ua.kpi.its.desktopcloud.database.dao.ActiveUserDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 17:22
 */
public class DaoHolder {
    private static ActiveUserDAO activeUserDAO;


    public static ActiveUserDAO getActiveUserDAO() throws IOException, SQLException {
        if (activeUserDAO == null){
            activeUserDAO = new ActiveUserDAO();
        }
        return activeUserDAO;
    }

}
