package ua.kpi.its.desktopcloud.server;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.kpi.its.desktopcloud.utils.Constants;
import ua.kpi.its.desktopcloud.utils.LogUtils;
import ua.kpi.its.desktopcloud.database.dao.ActiveUserDAO;
import ua.kpi.its.desktopcloud.database.utils.SupplicantUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 28.02.14
 * Time: 13:15
 */
public class ServerAgent extends Agent {
    private static final Logger LOGGER = Logger.getLogger(ServerAgent.class.getName());
    private static final long MONITORING_TIME_OUT = 10000l;

    public ServerAgent() {}

    @Override
    protected void setup() {
        LOGGER.addHandler(LogUtils.getServerLogHandler());
        LOGGER.info(Constants.DELIMITER);
        LOGGER.info("\n\tInitialising Server Agent...");

        try {
            register();
        } catch (FIPAException e) {
            LOGGER.log(Level.SEVERE, "Failed to register server: ", e);
            e.printStackTrace();
            takeDown();
        }

        addBehaviour(new ClientMonitor(this, MONITORING_TIME_OUT));

        addBehaviour(new Reciever(this));



    }

    @Override
    protected void takeDown() {
        LOGGER.info("\tDeleting all active users as the JADE server agent is going down...");

        try {
            ActiveUserDAO dao = new ActiveUserDAO();
            dao.deleteAll();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete all active users", e);
        }

        SupplicantUtils.limitBandwidthForAllSupplicants();

        LOGGER.info("\tTaking Server Agent down...");
    }

    private void register() throws FIPAException {
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.SERVER_DESCRIPTION);
        sd.setName(Constants.SERVER_DESCRIPTION);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);

        DFService.register(this, dfd);
    }

}
