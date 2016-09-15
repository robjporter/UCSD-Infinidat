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
public class INFINIDATdeleteVolumeByIDTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATdeleteVolumeByIDTask.class);
	
    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeleteVolumeIDTask:executeCustomAction ####---- CONTEXT: " + context );}
    	
    	actionlogger.addInfo("Delete ID Infinidat Volume task started.");
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		INFINIDATdeleteVolumeByIDTaskConfig config = (INFINIDATdeleteVolumeByIDTaskConfig) context.loadConfigObject();
		if (config == null) { throw new Exception("No Group config found for custom action " + context.getActionDef().getName() + " entryId " + configEntryId); }
    	actionlogger.addInfo("Delete ID Infinidat Volume task config loaded.");
    	
		actionlogger.addInfo("Delete ID Infinidat task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));
		
		try {
			INFINIDATFunctions func = new INFINIDATFunctions();
			func.setCredential(INFINIDATFunctions.getDeviceCredentials(config.getAccountName()));
			func.setAccountName(config.getAccountName());
			
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeleteVolumeByIDTask:executeCustomAction ####---- ACCNAME: " + config.getAccountName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATdeleteVolumeByIDTask:executeCustomAction ####---- ID: " + config.getVolumeID());}
			
			String result = func.deleteVolume(config.getVolumeID());
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATdeleteVolumeByIDTask:executeCustomAction ####---- RESULT: " + result);}
			actionlogger.addInfo("Delete ID Infinidat Volume task response from Infinidat array.");
	    	
			String id = "";
			String name = "";
			String serial = "";
			String size = "";
			String type = "";
			String used = "";
			String provtype = "";
			String code = "";
			String description = "";
			
	    	if( result != "" && result != null ) {
		    	String[] results = result.split( " | " );
		    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATdeletePoolByIDTask:executeCustomAction ####---- RESULTS LENGTH: " + results.length);}
				if(results.length > 0) {
					if(results[0].split(":")[1].trim().equals("SUCCESS")) {
						// SUCCEEDED
				        id = results[INFINIDATConstants.POS_VOLUME_ID].split(":")[1];
				        name = results[INFINIDATConstants.POS_VOLUME_NAME].split(":")[1];
				        serial = results[INFINIDATConstants.POS_VOLUME_SERIAL].split(":")[1];
				        size = results[INFINIDATConstants.POS_VOLUME_SIZE].split(":")[1];
				        type = results[INFINIDATConstants.POS_VOLUME_DATASET].split(":")[1];
				        used =  results[INFINIDATConstants.POS_VOLUME_USED].split(":")[1];
				        provtype = results[INFINIDATConstants.POS_VOLUME_PROVTYPE].split(":")[1];
				        code = results[INFINIDATConstants.POS_VOLUME_CODE].split(":")[1];
					} else {
						// FAILED
						code = results[INFINIDATConstants.POS_ERROR_CODE].split(":")[1].trim();
						description = results[INFINIDATConstants.POS_ERROR_DESCRIPTION].split(":")[1].trim();
					}
				}

		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_ID, id);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, name);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, serial);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, size);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, type);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_USED, used);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_PROVTYPE, provtype);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, code);
		        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, description);
			    actionlogger.addInfo("Create Infinidat Volume task output set - ID:  " + id);
			    actionlogger.addInfo("Create Infinidat Volume task output set - NAME:  " + name);
			    actionlogger.addInfo("Create Infinidat Volume task output set - SERIAL:  " + serial);
			    actionlogger.addInfo("Create Infinidat Volume task output set - SIZE:  " + size);
			    actionlogger.addInfo("Create Infinidat Volume task output set - TYPE:  " + type);
			    actionlogger.addInfo("Create Infinidat Volume task output set - USED:  " + used);
			    actionlogger.addInfo("Create Infinidat Volume task output set - PROVTYPE:  " + provtype);
			    actionlogger.addInfo("Create Infinidat Volume task output set - CODE:  " + code);
			    actionlogger.addInfo("Create Infinidat Volume task output set - DESCRIPTION:  " + description);
		    	
	    	}
	    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATdeleteVolumeIDTask:executeCustomAction ####---- UPDATING KNOWN VOLUMES");}
        	func.refreshVolumes();
        	actionlogger.addInfo("Delete Infinidat Volume task finished.");
        } catch (Exception e) {
            actionlogger.addWarning("Action " + INFINIDATdeleteVolumeByNameTaskConfig.displayLabel + ":" + e.getMessage());
            if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATdeleteVolumeIDTask:executeCustomAction ####---- ERROR: " + e.getMessage());}
            if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATdeleteVolumeIDTask:executeCustomAction ####---- STACKTRACE: " + e.getStackTrace());}
        }
    }
    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeleteVolumeIDTask:getTaskOutputDefinitions ####----");}
		TaskOutputDefinition[] ops = new TaskOutputDefinition[9];
   		ops[0] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_ID, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume ID");
   		ops[1] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Name");
   		ops[2] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Serial");
   		ops[3] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Size");
   		ops[4] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Type");
   		ops[5] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_USED, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Used");
   		ops[6] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_PROVTYPE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Prov Type");
   		ops[7] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "HTTP Code");
   		ops[8] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Error Description");
   		return ops;
    }
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeleteVolumeIDTask:getTaskConfigImplementation ####----");}
		return new INFINIDATdeleteVolumeByIDTaskConfig();
	}
	@Override
	public String getTaskName() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATdeleteVolumeIDTask:getTaskName ####----");}
		return INFINIDATdeleteVolumeByIDTaskConfig.displayLabel;
	}
}