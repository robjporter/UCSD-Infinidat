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

@SuppressWarnings("unused")
public class INFINIDATdeletePoolByIDTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATdeletePoolByIDTask.class);
	
    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- CONTEXT: " + context );}

    	actionlogger.addInfo("Delete Infinidat Pool task started.");
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		INFINIDATdeletePoolByIDTaskConfig config = (INFINIDATdeletePoolByIDTaskConfig) context.loadConfigObject();
		if (config == null) { throw new Exception("No Group config found for custom action " + context.getActionDef().getName() + " entryId " + configEntryId);}
		actionlogger.addInfo("Delete Infinidat Pool task config loaded.");
		
		actionlogger.addInfo("Delete Infinidat task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));
		
		try {
			INFINIDATFunctions func = new INFINIDATFunctions();
			func.setCredential(INFINIDATFunctions.getDeviceCredentials(config.getAccountName()));
			func.setAccountName(config.getAccountName());
			
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- ACCNAME: " + config.getAccountName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- ID: " + config.getPoolID());}
			
			String result = func.deletePool(config.getPoolID());
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- RESULT: " +  result);}
			actionlogger.addInfo("Delete Infinidat Pool task response from Infinidat array.");
	    	
			String id = "";
			String name = "";
			String state = "";
			String size = "";
			String description = "";
			String code = "";
			
			if( result != "" && result != null ) {
		    	String results[] = result.split( "\\|" );
		    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- RESULTS LENGTH: " + results.length);}
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
				
		    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- UPDATING KNOWN VOLUMES");}
		    	func.refreshPools();
	        	actionlogger.addInfo("Delete Infinidat Pool task finished.");
	    	}
		} catch(Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- ERROR: " + e.getMessage());}
			actionlogger.addWarning("Action " + INFINIDATdeletePoolByIDTaskConfig.displayLabel + ":" + e.getMessage());
		}
    }

    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####----");}
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
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:getTaskConfigImplementation ####----");}
		return new INFINIDATdeletePoolByIDTaskConfig();
	}
	@Override
	public String getTaskName() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeletePoolByIDTask:getTaskName ####----");}
		return INFINIDATdeletePoolByIDTaskConfig.displayLabel;
	}
}