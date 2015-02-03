package ua.kpi.its.desktopcloud.client;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Сергей on 27.12.2014.
 */
public class ClientUtils {

    private static final Logger logger = Logger.getLogger(ClientUtils.class.getName());
    private static Properties properties;

    public static Properties getAgentProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(ClientUtils.class.getClassLoader().getResourceAsStream("agent.properties"));
                logger.log(Level.INFO, "Agent properties loaded");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to load properties");
            }

        }
        return properties;
    }

    public static String getAgentProperty(@NotNull String key) {
        return getAgentProperties().getProperty(key);
    }
}
