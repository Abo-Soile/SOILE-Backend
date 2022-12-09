package fi.abo.kogni.soile2.projecthandling.participant;

import io.vertx.core.json.JsonObject;
/**
 * Interface to create Participants, to keep the ParticipantManager flexible.
 * @author Thomas Pfau
 *
 */
public interface ParticipantFactory {	
	
	public Participant createParticipant(JsonObject data);
}
