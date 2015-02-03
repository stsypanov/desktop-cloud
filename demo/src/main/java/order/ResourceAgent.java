package demo.order;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 17.02.14
 * Time: 12:37
 */
public class ResourceAgent extends Agent {
    protected void setup() {
        addBehaviour(new SimpleBehaviour(this) {
            private boolean finished = false;
            public String DestinationPoint;

            public void action() {
                System.out.println(getLocalName() + " is active");
//                System.out.println(getLocalName() + " Enter input destination point: ");

                DestinationPoint = "Moscow";
                MessageTemplate m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                MessageTemplate m2 = MessageTemplate.MatchOntology("TestOntology");
                MessageTemplate m3 = MessageTemplate.and(m1, m2);
                ACLMessage msg = blockingReceive(m3, 60000);

                if (msg != null) {
                    System.out.println(getLocalName() + ": message from " + msg.getSender().getLocalName() + " was received");
                    System.out.println(getLocalName() + ": " + msg.getContent());
                    if (DestinationPoint.equals(msg.getContent()))
                        System.out.println(getLocalName() + ": order was accepted");
                    else
                        System.out.println(getLocalName() + ": order was rejected");
                } else {
                    System.out.println(getLocalName() + ": empty message was received");
                }
                finished = true;
            }

            public boolean done() {
                return finished;
            }
        });
    }
}

