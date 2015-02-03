package ua.kpi.its.desktopcloud.client;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 28.02.14
 * Time: 13:38
 */
public class RequestPerformer extends Behaviour {
    private Set<AID> servers;
    private Agent myAgent;

    public RequestPerformer(Agent myAgent, Set<AID> servers) {
        this.myAgent = myAgent;
        this.servers = servers;
    }

    @Override
    public void action() {
        ACLMessage requestMessage = new ACLMessage(ACLMessage.QUERY_IF);
        requestMessage.setContent("");
//        requestMessage.addReceiver();
        myAgent.send(requestMessage);
    }

    @Override
    public boolean done() {
        return false;
    }
}
