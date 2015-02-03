package ua.kpi.its.desktopcloud.client;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.hibernate.HibernateException;
import sun.plugin.util.UIUtil;
import ua.kpi.its.desktopcloud.client.ui.AgentUI;
import ua.kpi.its.desktopcloud.utils.Constants;
import ua.kpi.its.desktopcloud.utils.LogUtils;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.dao.ActiveUserDAO;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 01.12.13
 * Time: 14:47
 */
public class ClientAgent extends Agent {
    private static Logger LOGGER = Logger.getLogger(ClientAgent.class.getName());
    private Set<AID> servers;
    private AgentUI ui;
    private ActiveUserDAO dao;
    private ActiveUser activeUser;

    public ClientAgent() {
    }

    @Override
    protected void setup() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui = new AgentUI(ClientAgent.this);
                ui.showUI();
            }
        });

        LOGGER.addHandler(LogUtils.getClientLogHandler());
        LOGGER.info(Constants.DELIMITER);
        LOGGER.info("Agent " + getAID().getName() + " enabled");

        try {
            findServer();
        } catch (FIPAException e) {
            LOGGER.log(Level.SEVERE, "Failed to find server: ", e);
        }

        addBehaviour(new Notifier(this, Constants.NOTIFICATION_TIME_OUT, servers));
        addBehaviour(new RequestPerformer(this, servers));
    }

    @Override
    public void takeDown() {
        LOGGER.info("Setting bandwidth limit for user " + getAID().getName() + " as agent is being taken down.");

        if (dao != null && activeUser != null) {
            dao.delete(activeUser);
        }

        System.out.println("Taking down " + (getAID().getName()) + Constants.DELIMITER);
        LOGGER.info("Taking down " + getAID().getName());
    }

    public void register(String name, String password) throws FIPAException, HibernateException {
        getAID().setName(name);

        try {
            dao = new ActiveUserDAO();
            activeUser = dao.save(name, password);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save active user " + name, e);
        }

        DFAgentDescription template = prepareTemplate(Constants.CLIENT_DESCRIPTION);

        DFService.register(this, template);

        LOGGER.info("Active user with username: " + name + " and password: " + password + " now registered.");
    }

    private void findServer() throws FIPAException {
        DFAgentDescription template = prepareTemplate(Constants.SERVER_DESCRIPTION);

        DFAgentDescription[] result = DFService.search(this, template);

        servers = new HashSet<AID>(result.length);

        for (DFAgentDescription description : result) {
            servers.add(description.getName());
        }
    }

    private DFAgentDescription prepareTemplate(String description) {
        ServiceDescription sd = new ServiceDescription();
        sd.setType(description);
        sd.setName(description);

        DFAgentDescription template = new DFAgentDescription();
        template.addServices(sd);
        return template;
    }
}
