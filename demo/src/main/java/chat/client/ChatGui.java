
package demo.chat.client;

/**
   @author Giovanni Caire - TILAB
 */
public interface ChatGui {
	void notifyParticipantsChanged(String[] names);
	void notifySpoken(String speaker, String sentence);
	void dispose();
}