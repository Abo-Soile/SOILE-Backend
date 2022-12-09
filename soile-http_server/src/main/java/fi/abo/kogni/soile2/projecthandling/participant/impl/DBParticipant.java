package fi.abo.kogni.soile2.projecthandling.participant.impl;

import fi.abo.kogni.soile2.projecthandling.participant.ParticipantImpl;
import fi.abo.kogni.soile2.projecthandling.participant.DataParticipantImpl;
import fi.abo.kogni.soile2.projecthandling.participant.ParticipantManager;
import fi.abo.kogni.soile2.utils.SoileConfigLoader;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * This class represents a participant stored in a database.
 * The DB Participant has the following spec:
 *   Participant:
      description: An Entry in this database represents one participant in one project.
                   Data relevant for this project is stored in each document e.g. the position in the project, data from the project etc. 
      type: object
      properties:
        _id: 
          type: string
          description: The UUID of this participant. Has to have the form of a uuid
        project:
          type: string
          description: The project this participant is in.
        position: 
          type: string
          description: the uuid of the element in the project this user is currently at. Has to have the form of a uuid
          default: the project uuid if the user just started.
        finishedExperimentTasks: 
          type: array
          items:
            type: object
            properties: 
              experimentID:
                type: string
                description: uuids of the experiment with finished tasks
              tasks:
                type: array
                items:
                  type: string
                  description: uuids of the experiment with finished tasks
        outputData: 
          type: array
          items:
            type: object
            required: 
              - task
              - outputs
            properties:
              task: 
                type: string
                description: The task for which data is being stored. Contains one entry for each defined output of a task.
              outputs:
                type: array
                items:
                  type: object
                  required: 
                    - name
                    - value
                  properties:
                    name: 
                      type: string
                      description: the name of the output, the format is "[0-9A-Za-z]+"
                      example: "smoker"
                    value:
                      type: number
                      description: The value of an output                
                    timestamp:
                      type: string
                      format: date                      

 * @author Thomas Pfau
 * 
 */
public class DBParticipant extends ParticipantImpl{
	
	private ParticipantManager manager;
	private String participantCollection = SoileConfigLoader.getdbProperty("participantCollection");
	public DBParticipant(JsonObject data, ParticipantManager manager)
	{
		super(data);
		this.manager = manager;
	}		
	
	@Override
	public Future<String> save()
	{		
		return manager.save(this);
	}

	@Override
	public Future<Void> addResult(String taskID, JsonObject result) {
		Promise<Void> resultPromise = Promise.promise(); 
		getCurrentStep()
		.onSuccess(cstep ->
		{
			manager.updateResults(this, cstep, taskID, result);
			resultPromise.complete();
		})
		.onFailure(err -> resultPromise.fail(err));
				
		return resultPromise.future();
	}

	@Override
	public Future<Integer> getCurrentStep() {
		// TODO Auto-generated method stub
		return Future.succeededFuture(currentStep);
	}
	
	

}