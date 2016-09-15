package com.cloupia.feature.infinidat.tasks;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.TaskOutputDefinition;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionLogger;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionTriggerContext;

public class INFINIDATeditClusterTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATeditClusterTask.class);

	@Override
	public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
		logger.info( "----#### INFINIDATeditClusterTask:executeCustomAction ####---- CONTEXT: " + context );

    	long configEntryId = context.getConfigEntry().getConfigEntryId();
    	INFINIDATeditClusterTaskConfig config = (INFINIDATeditClusterTaskConfig) context.loadConfigObject();

		if (config == null) {
			throw new Exception("No Group config found for custom action " + context.getActionDef().getName()+ " entryId " + configEntryId);
		}

		//the MoTaskAPI is sort of a shortcut way to use the REST API
		//MoTaskAPI api = MoTaskAPI.getInstance();
		actionlogger.addInfo("The task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));

	}
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		return new INFINIDATeditClusterTaskConfig();
	}
	@Override
	public String getTaskName() {
		return INFINIDATeditClusterTaskConfig.displayLabel;
	}
	@Override
	public TaskOutputDefinition[] getTaskOutputDefinitions() {
		return null;
	}
}
