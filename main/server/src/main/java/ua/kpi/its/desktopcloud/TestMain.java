package ua.kpi.its.desktopcloud;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 12.02.14
 * Time: 11:29
 */
public class TestMain {
    public static void main(String[] args) {
        String property = System.getProperties().getProperty("java.class.path");
        StringTokenizer st = new StringTokenizer(property, ";");
        while (st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
        System.out.println("Test class OK");
    }
}