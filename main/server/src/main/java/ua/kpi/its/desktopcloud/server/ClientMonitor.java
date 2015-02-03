package ua.kpi.its.desktopcloud.server;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.kpi.its.desktopcloud.exception.DatabaseException;
import ua.kpi.its.desktopcloud.database.dao.ActiveUserDAO;
import ua.kpi.its.desktopcloud.database.dao.SupplicantDAO;
import ua.kpi.its.desktopcloud.database.entity.ActiveUser;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;
import ua.kpi.its.desktopcloud.database.utils.SupplicantUtils;
import ua.kpi.its.desktopcloud.utils.Constants;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 03.03.14
 * Time: 17:21
 * This class
 */
public class ClientMonitor extends TickerBehaviour {
    private static final Logger LOGGER = Logger.getLogger(ClientMonitor.class.getName());
    private Set<AID> clients;


    public ClientMonitor(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        try {
            proceed();
        } catch (DatabaseException e) {
            LOGGER.log(Level.SEVERE, "Failed to check permissions", e);
        }
        checkAccessPermission();
    }

    private void proceed() throws DatabaseException {
        List<Supplicant> supplicants = getAllSupplicants();

        if (!supplicants.isEmpty()){
            List<ActiveUser> activeUsers = getAllActiveUsers();

            if (activeUsers.isEmpty()){
                limitBandwidthForAllSupplicants();
            } else {
                checkSupplicant(supplicants, activeUsers);
            }
            
        }

//        Set<AID> clients;
//        try {
//             clients = getAllClients();
//            for (AID ua.kpi.its.desktopcloud.client : clients){
//                checkIfPresentInDatabase(ua.kpi.its.desktopcloud.client);
//            }
//        } catch (FIPAException e) {
//            e.printStackTrace();
//        }

    }

    private void checkSupplicant(List<Supplicant> supplicants, List<ActiveUser> activeUsers) {

    }

    private void limitBandwidthForAllSupplicants() {
        SupplicantUtils.limitBandwidthForAllSupplicants();
    }

    private List<Supplicant> getAllSupplicants() throws DatabaseException {
        SupplicantDAO dao;
        try {
            dao = new SupplicantDAO();
        } catch (IOException e) {
            throw new DatabaseException("Failed to instantiate SupplicantDAO", e);
        }
        return dao.getAll();
    }

    private List<ActiveUser> getAllActiveUsers() throws DatabaseException {
        ActiveUserDAO dao;
        try {
            dao = new ActiveUserDAO();
        } catch (IOException e) {
            throw new DatabaseException("Failed to instantiate ActiveUserDAO", e);
        }
        return dao.getAll();
    }

    private Set<AID> getAllClients() throws FIPAException {
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.CLIENT_DESCRIPTION);
        sd.setName(Constants.CLIENT_DESCRIPTION);

        DFAgentDescription template = new DFAgentDescription();
        template.addServices(sd);
        DFAgentDescription[] result = DFService.search(myAgent, template);

        clients = new HashSet<AID>();
        for (DFAgentDescription agentDescription : result) {
            clients.add(agentDescription.getName());
        }
        return clients;
    }

    private void checkAccessPermission() {
        for (AID client : clients) {
            checkIfPresentInDatabase(client);
        }
    }

    private void checkIfPresentInDatabase(AID client) {
        String name = client.getName();

    }

    public Set<AID> getClients() {
        return clients;
    }


}
