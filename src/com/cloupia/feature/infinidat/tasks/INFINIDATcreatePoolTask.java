package com.cloupia.feature.infinidat.tasks;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.WorkflowInputFieldTypeDeclaration;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.TaskOutputDefinition;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionLogger;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionTriggerContext;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
public class INFINIDATcreatePoolTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATcreatePoolTask.class);

    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreatePoolTask:executeCustomAction ####---- CONTEXT: " + context );}
    	
    	actionlogger.addInfo("Create Infinidat Pool task started.");
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		INFINIDATcreatePoolTaskConfig config = (INFINIDATcreatePoolTaskConfig) context.loadConfigObject();
		if (config == null){throw new Exception("No Group config found for custom action " + context.getActionDef().getName() + " entryId " + configEntryId);}
		actionlogger.addInfo("Create Infinidat Pool task config loaded.");
		
		actionlogger.addInfo("Create Infinidat task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));

		try {
			INFINIDATFunctions func = new INFINIDATFunctions();
			func.setCredential(INFINIDATFunctions.getDeviceCredentials(config.getAccountName()));
			func.setAccountName(config.getAccountName());
			
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- ACCNAME: " + config.getAccountName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreatePoolTask:executeCustomAction ####---- NAME: " + config.getPoolName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreatePoolTask:executeCustomAction ####---- MEASURE: " + config.getMeasure());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreatePoolTask:executeCustomAction ####---- PHYSICAL: " + config.getPhysicalSize());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreatePoolTask:executeCustomAction ####---- VIRTUAL: " + config.getVirtualSize());}
			
			String result = func.createNewPool(config.getPoolName(), config.getMeasure(), false, myFormat.safeLongToString(config.getPhysicalSize()), myFormat.safeLongToString(config.getVirtualSize()), false);
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- RESULT: " +  result);}
			actionlogger.addInfo("Create Infinidat Pool task response from Infinidat array.");
	    	
			String id = "";
			String name = "";
			String state = "";
			String size = "";
			String description = "";
			String code = "";
			
			if(result != null) {
				String results[] = result.split("\\|");
				if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATcreatePoolTask:executeCustomAction ####---- RESULTS LENGTH: " + results.length);}
				if(results.length > 0) {
					if(results[0].split(":")[1].trim().equals("SUCCESS")) {
						// SUCCEEDED
						id = results[INFINIDATConstants.POS_POOL_ID].split(":")[1].trim();
						name = results[INFINIDATConstants.POS_POOL_NAME].split(":")[1].trim();
						state = results[INFINIDATConstants.POS_POOL_STATE].split(":")[1].trim();
						size = results[INFINIDATConstants.POS_POOL_PHYSICAL].split(":")[1].trim();
						code = results[INFINIDATConstants.POS_POOL_CODE].split(":")[1].trim();
					} else {
						// FAILED
						code = results[INFINIDATConstants.POS_ERROR_CODE].split(":")[1].trim();
						description = results[INFINIDATConstants.POS_ERROR_DESCRIPTION].split(":")[1].trim();
					}
				}

	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_POOL_ID, id);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_POOL_NAME, name);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_POOL_STATE, state);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_POOL_SIZE, size);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, code);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, description);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - ID:  " + id);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - NAME:  " + name);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - STATE:  " + state);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - SIZE:  " + size);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - CODE:  " + code);
	        	actionlogger.addInfo("Create Infinidat Pool task output set - DESCRIPTION:  " + description);
			}
		    	
	    	
		    if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreatePoolTask:executeCustomAction ####---- UPDATING KNOWN VOLUMES");}
        	func.refreshPools();
        	actionlogger.addInfo("Create Infinidat Pool task finsihed.");
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATcreatePoolTask:executeCustomAction ####---- ERROR: " + e.getMessage());}
			actionlogger.addWarning("Action " + INFINIDATcreatePoolTaskConfig.displayLabel + ":" + e.getMessage());
		}
    }
    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreatePoolTask:getTaskOutputDefinitions ####----");}
   		TaskOutputDefinition[] ops = new TaskOutputDefinition[6];
   		ops[0] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_POOL_ID,  WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Pool ID");
   		ops[1] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_POOL_NAME, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Pool Name");
   		ops[2] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_POOL_STATE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Pool State");
   		ops[3] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_POOL_SIZE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Pool Size");
   		ops[4] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "HTTP Code");
   		ops[5] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Error Description");
   		return ops;
    }
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreatePoolTask:getTaskConfigImplementation ####----");}
    	return new INFINIDATcreatePoolTaskConfig();
	}
	@Override
	public String getTaskName() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreatePoolTask:getTaskName ####----");}
    	return INFINIDATcreatePoolTaskConfig.displayLabel;
	}
}
