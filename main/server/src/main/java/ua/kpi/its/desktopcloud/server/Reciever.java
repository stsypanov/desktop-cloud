package ua.kpi.its.desktopcloud.server;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ua.kpi.its.desktopcloud.utils.Constants;
import ua.kpi.its.desktopcloud.utils.LogUtils;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 03.03.14
 * Time: 10:22
 */
public class Reciever extends CyclicBehaviour {
    private static final Logger LOGGER = Logger.getLogger(CyclicBehaviour.class.getName());

    public Reciever(Agent myAgent) {
        super(myAgent);
        LOGGER.addHandler(LogUtils.getServerLogHandler());
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchConversationId(Constants.AGENT_NOTIFICATION_CONVERASTION_ID);
        ACLMessage message = myAgent.receive(mt);
        if (message != null){
            LOGGER.info("Message received:\t" + message.getContent());
        } else {
            block();
        }
    }
}
