package demo.chat.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.CFReflectiveIntrospector;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

/**
 * Ontology containing concepts, predicates and actions used within the chat
 * application.
 *
 * @author Giovanni Caire - TILAB
 */
public class ChatOntology extends Ontology implements ChatVocabulary {

    // The singleton instance of this ontology
    private static Ontology theInstance = new ChatOntology();

    public static Ontology getInstance() {
        return theInstance;
    }

    public ChatOntology(String name, Ontology base) {
        super(name, base);
    }

    /**
     * Constructor
     */
    private ChatOntology() {
        //#PJAVA_EXCLUDE_BEGIN
        /*#J2ME_INCLUDE_BEGIN
        //#PJAVA_EXCLUDE_END
		super(ONTOLOGY_NAME, BasicOntology.getInstance(), null);

		try {
			add(new PredicateSchema(JOINED));
			add(new PredicateSchema(LEFT));
			add(new PredicateSchema(SPOKEN));
		//#PJAVA_EXCLUDE_BEGIN
		#J2ME_INCLUDE_END*/
        //#PJAVA_EXCLUDE_END
        //#J2ME_EXCLUDE_BEGIN
        super(ONTOLOGY_NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());

        try {
            add(new PredicateSchema(JOINED), Joined.class);
            add(new PredicateSchema(LEFT), Left.class);
            add(new PredicateSchema(SPOKEN), Spoken.class);
            //#J2ME_EXCLUDE_END
            PredicateSchema ps = (PredicateSchema) getSchema(JOINED);
            ps.add(JOINED_WHO, (ConceptSchema) getSchema(BasicOntology.AID), 1, ObjectSchema.UNLIMITED);

            ps = (PredicateSchema) getSchema(LEFT);
            ps.add(LEFT_WHO, (ConceptSchema) getSchema(BasicOntology.AID), 1, ObjectSchema.UNLIMITED);

            ps = (PredicateSchema) getSchema(SPOKEN);
            ps.add(SPOKEN_WHAT, (PrimitiveSchema) getSchema(BasicOntology.STRING));
        } catch (OntologyException e) {
            e.printStackTrace();
        }
    }

}
