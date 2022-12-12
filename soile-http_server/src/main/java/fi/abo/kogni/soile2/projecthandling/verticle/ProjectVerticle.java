package fi.abo.kogni.soile2.projecthandling.verticle;

import fi.abo.kogni.soile2.datamanagement.git.GitManager;
import fi.abo.kogni.soile2.datamanagement.git.ObjectManager;
import fi.abo.kogni.soile2.datamanagement.git.ResourceManager;
import fi.abo.kogni.soile2.datamanagement.utils.TimeStampedMap;
import fi.abo.kogni.soile2.projecthandling.participant.ParticipantHandler;
import fi.abo.kogni.soile2.projecthandling.projectElements.instance.ProjectInstance;
import fi.abo.kogni.soile2.projecthandling.projectElements.instance.impl.ProjectInstanceHandler;
import fi.abo.kogni.soile2.projecthandling.projectElements.instance.impl.ProjectInstanceManager;


public class ProjectVerticle {

	private ResourceManager resourceManager;
	private ObjectManager objectManager;
	private ProjectInstanceHandler projectInstanceHandler;
	private ParticipantHandler participantHandler;
	private GitManager gitManager;	

	public void createProject(Message<Object> request)
	{		
		Promise<Boolean> creationPromise = Promise.<Boolean>promise();
		// we might need to change this at some point, but for now, just request a creation with the name as body.
		String name = request.body().toString();
		projectManager.getCurrentUUIDs()
		.onSuccess(uuids -> {
			UUID projectID;
			projectID = UUID.randomUUID();			
			// now we need to create a unique UUID.
			while(uuids.contains(projectID.toString()))
			{
				projectID = UUID.randomUUID();
			}			
			final UUID selectedID = projectID;
			gitManager.initRepo(projectID.toString())
			.onSuccess(initVersion -> 
			{
				JsonObject gitData = GitManager.buildBasicGitProject(name);
				gitManager.writeGitFile(new GitFile("Object.json", selectedID.toString(), initVersion), gitData).onSuccess(version -> {

					// store the created project.
					projectManager.createProject(selectedID.toString(), version)
					.onSuccess(instance -> {
						// store this in the Mongo Database.
						//TODO: Project Management for non intantated projects
						request.reply(SoileCommUtils.successObject()
									  .put("project", gitData.put("version", version)
											                 .put("UUID", selectedID)
											                 .put("private", false)));
					})
					.onFailure(err -> {
						request.fail(500, "Couldn't write file");
					});
				})
				.onFailure(err -> {
					request.fail(500, "Couldn't create Project");
				});
			})
			.onFailure(err -> 
			{
				request.fail(500, "Couldn't initialize Repository");
			});
		})
		.onFailure(err -> {
			request.fail(500, "Couldn't retrieve UUIDs");
		});
	}*/

}
