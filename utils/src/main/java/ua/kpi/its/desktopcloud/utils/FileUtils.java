package ua.kpi.its.desktopcloud.utils;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 25.02.14
 * Time: 15:57
 */
public class FileUtils {
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());
    public static final String USER_HOME = "user.home";

    public static String DELIMITER;
    public static final String LOG_FILES_DIR = "DesctopCloud";
    public static final String WINDOWS = "windows";
    public static final String DELIMITER_WIN = "\\";
    public static final String DELIMITER_UNIX = "/";

    public static String createDatabaseLog(){
        checkDelimiter();
        final String homeDir = System.getProperties().getProperty(USER_HOME);
        final String pathToLogDir = homeDir + DELIMITER + LOG_FILES_DIR + DELIMITER;
        final File logFilesDir = new File(pathToLogDir);

        if (!logFilesDir.exists()){
            if (logFilesDir.mkdir()){
                LOGGER.info("Log folder successfully created in ".concat(homeDir));
            } else {
                LOGGER.info("Failed to create log folder in ".concat(homeDir));
            }
        }
        return pathToLogDir + "db-log.txt";
    }

    public static String createClientLog(){
        checkDelimiter();
        final String homeDir = System.getProperties().getProperty(USER_HOME);
        final String pathToLogDir = homeDir + DELIMITER + LOG_FILES_DIR + DELIMITER;
        final File logFilesDir = new File(pathToLogDir);

        if (!logFilesDir.exists()) {
            if (logFilesDir.mkdir()){
                LOGGER.info("Log folder successfully created in ".concat(homeDir));
            } else {
                LOGGER.info("Failed to create log folder in ".concat(homeDir));
            }
        }
        return pathToLogDir + "client-log.txt";
    }

    public static String createServerLog(){
        checkDelimiter();
        final String homeDir = System.getProperties().getProperty(USER_HOME);
        final String pathToLogDir = homeDir + DELIMITER + LOG_FILES_DIR + DELIMITER;
        final File logFilesDir = new File(pathToLogDir);

        if (!logFilesDir.exists()) {
            if (logFilesDir.mkdir()){
                LOGGER.info("Log folder successfully created in ".concat(homeDir));
            } else {
                LOGGER.info("Failed to create log folder in ".concat(homeDir));
            }
        }
        return pathToLogDir + "server-log.txt";
    }

    public static String checkOS(){
        return System.getProperties().getProperty("os.name");
    }

    public static void checkDelimiter() {
        if (checkOS().toLowerCase().contains(WINDOWS)){
            DELIMITER = DELIMITER_WIN;
        } else {
            DELIMITER = DELIMITER_UNIX;
        }
    }
}
