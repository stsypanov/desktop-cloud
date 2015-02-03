package ua.kpi.its.desktopcloud.client.wrapper;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ua.kpi.its.desktopcloud.client.ClientUtils;
import ua.kpi.its.desktopcloud.utils.LogUtils;


import javax.swing.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stsypanov on 29.11.14.
 */
public class AgentWrapper {
    private static final Logger LOGGER = Logger.getLogger(AgentWrapper.class.getName());

    private static final String ERROR_MESSAGE = "Failed to start agent";
    private static final String LOCAL_HOST = "local-host";
    private static final String LOCAL_PORT = "local-port";

    public static void main(String[] args) {
        LOGGER.addHandler(LogUtils.getClientLogHandler());
        LOGGER.info("Starting agent ...");

        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(LOCAL_HOST, ClientUtils.getAgentProperty(LOCAL_HOST));
        profile.setParameter(LOCAL_PORT, ClientUtils.getAgentProperty(LOCAL_PORT));

        ContainerController controller = runtime.createAgentContainer(profile);

        String agentName = System.getProperties().getProperty("user.name");
        createAgent(controller, agentName);
    }

    public static void createAgent(ContainerController controller, String agentName) {
        try {
            String agentProperty = ClientUtils.getAgentProperty("agent-class");
            AgentController agent = controller.createNewAgent(agentName, agentProperty, new Object[0]);
            agent.start();
        } catch (StaleProxyException e) {
            LOGGER.log(Level.SEVERE, ERROR_MESSAGE, e);
            JOptionPane.showMessageDialog(null, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
