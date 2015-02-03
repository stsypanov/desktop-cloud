package ua.kpi.its.desktopcloud.database.utils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 15.04.2014
 * Time: 12:55
 */
public class StringUtils {
    public static final String CLEAR_TEXT_PASSWORD_ATTRIBUTE = "Cleartext-Password";
    public static final String EQUALS_SIGN = ":=";

    public static String generateRandomString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (byte i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
