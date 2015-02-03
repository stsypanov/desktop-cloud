package ua.kpi.its.desktopcloud.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 27.02.14
 * Time: 18:23
 */
public class LogUtils {

    private static FileHandler clientLogHandler;
    private static FileHandler serverLogHandler;
    private static FileHandler databaseLogHandler;

    private static final int LOG_SIZE = 1048576;
    private static final int LOG_FILE_COUNT = 10;

    static {
        String databaseLog = FileUtils.createDatabaseLog();

        try{
            databaseLogHandler = new FileHandler(databaseLog, LOG_SIZE, LOG_FILE_COUNT, true);
            databaseLogHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e){
            System.err.println("Failed to create database log:");
            e.printStackTrace();
        }

        String clientLog = FileUtils.createClientLog();

        try {
            clientLogHandler = new FileHandler(clientLog, LOG_SIZE, LOG_FILE_COUNT, true);
            clientLogHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            System.err.println("Failed to create client log:");
            e.printStackTrace();
        }

        String serverLog = FileUtils.createServerLog();

        try {
            serverLogHandler = new FileHandler(serverLog, LOG_SIZE, LOG_FILE_COUNT, true);
            serverLogHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e){
            System.err.println("Failed to create server log:");
            e.printStackTrace();
        }

    }

    public static FileHandler getDatabaseLogHandler() {
        return databaseLogHandler;
    }

    public static FileHandler getClientLogHandler() {
        return clientLogHandler;
    }

    public static FileHandler getServerLogHandler() {
        return serverLogHandler;
    }
}
