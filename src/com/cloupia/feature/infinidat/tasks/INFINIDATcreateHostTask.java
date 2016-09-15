package com.cloupia.feature.infinidat.tasks;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.TaskOutputDefinition;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionLogger;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionTriggerContext;

public class INFINIDATcreateHostTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATcreateHostTask.class);
	
    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	logger.info( "----#### INFINIDATcreateHostTask:executeCustomAction ####---- CONTEXT: " + context );
    	
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		//retrieving the corresponding config object for this handler
		INFINIDATcreateHostTaskConfig config = (INFINIDATcreateHostTaskConfig) context.loadConfigObject();

		if (config == null)
		{
			throw new Exception("No Group config found for custom action " + context.getActionDef().getName()
					+ " entryId " + configEntryId);
		}

		actionlogger.addInfo("The task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));

    }

    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
   		return null;
    }
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		// TODO Auto-generated method stub
		return new INFINIDATcreateHostTaskConfig();
	}
	@Override
	public String getTaskName() {
		// TODO Auto-generated method stub
		return INFINIDATcreateHostTaskConfig.displayLabel;
	}
}
