package ua.kpi.its.desktopcloud.client;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ua.kpi.its.desktopcloud.utils.Constants;
import ua.kpi.its.desktopcloud.utils.LogUtils;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 28.02.14
 * Time: 16:47
 */
public class Notifier extends TickerBehaviour {
    private static final Logger LOGGER = Logger.getLogger(Notifier.class.getName());
    private Set<AID> servers;

    public Notifier(Agent a, long period, Set<AID> servers) {
        super(a, period);
        this.servers = servers;

        LOGGER.addHandler(LogUtils.getClientLogHandler());
    }


    @Override
    protected void onTick() {
        ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
        notification.setContent(myAgent.getAID().getName() + " " + getAgent().getAgentState().toString());
        notification.setConversationId(Constants.AGENT_NOTIFICATION_CONVERASTION_ID);
        String serversList = "";

        for (AID server : servers){
            notification.addReceiver(server);
            serversList += server.getName() + " ";
        }
        LOGGER.log(Level.INFO, "Sending notification message to servers: " + serversList);
        myAgent.send(notification);
    }
}
