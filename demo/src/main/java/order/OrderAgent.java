package demo.order;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 17.02.14
 * Time: 11:01
 */
public class OrderAgent extends Agent {

    protected void setup() {
        addBehaviour(new SimpleBehaviour(this) {
            private boolean finished = false;
            public String DestinationPoint = "Moscow";
            AID[] resources = {
                    new AID("Resource1@127.0.0.1:1098/JADE", true),
                    new AID("Resource2@127.0.0.1:1098/JADE", true),
                    new AID("Resource3@127.0.0.1:1098/JADE", true),
                    new AID("Resource4@127.0.0.1:1098/JADE", true),
                    new AID("Resource5@127.0.0.1:1098/JADE", true)};

            public void action() {
                doWait(3000);
                System.out.println(getLocalName() + " is active");
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setOntology("TestOntology");
                msg.setContent(DestinationPoint);
                for (AID resource : resources){
                    msg.addReceiver(resource);
                }
                send(msg);
                finished = true;
            }

            public boolean done() {
                System.out.println(getLocalName() + ": all messages sent");
                return finished;
            }
        });
    }
}
