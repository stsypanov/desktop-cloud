package ua.kpi.its.desktopcloud.exception;

/**
 * Created by stsypanov on 29.04.14.
 */
public class DatabaseException extends Exception {
    public DatabaseException(String message, Throwable e) {
        super(message, e);
    }
}
