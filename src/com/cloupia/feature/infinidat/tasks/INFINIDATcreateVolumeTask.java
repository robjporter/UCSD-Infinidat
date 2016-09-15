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
public class INFINIDATcreateVolumeTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATcreateVolumeTask.class);

    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- CONTEXT: " + context );}
    	
    	actionlogger.addInfo("Create Infinidat Volume task started.");
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		INFINIDATcreateVolumeTaskConfig config = (INFINIDATcreateVolumeTaskConfig) context.loadConfigObject();
		if (config == null) { throw new Exception("No Group config found for custom action " + context.getActionDef().getName() + " entryId " + configEntryId); }
    	actionlogger.addInfo("Create Infinidat Volume task config loaded.");

		actionlogger.addInfo("Create Infinidat task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));
		
		try {
			INFINIDATFunctions func = new INFINIDATFunctions();
			func.setCredential(INFINIDATFunctions.getDeviceCredentials(config.getAccountName()));
			func.setAccountName(config.getAccountName());

			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- ACCNAME: " + config.getAccountName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- VOLUME NAME: " + config.getVolumeName());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- VOLUME UNIT: " + config.getVolumeSizeUnit());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- VOLUME SIZE: " + config.getVolumeSize());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- VOLUME PROV TYPE: " + config.getVolumeProvisionType());}
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- VOLUME POOL ID: " + config.getPoolProvisionID());}
			
			String result = func.createNewVolume(config.getVolumeName(),config.getVolumeSizeUnit(),config.getVolumeSize(),config.getVolumeProvisionType(),config.getPoolProvisionID(),true);
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- RESULT: " + result);}
			actionlogger.addInfo("Create Infinidat Volume task response from Infinidat array.");
	    	
			String id = "";
			String name = "";
			String type = "";
			String size = "";
			String serial = "";
			String description = "";
			String code = "";
			
			if( result != "" && result != null ) {
		    	String[] results = result.split( "\\|" );
		    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- RESULTS LENGTH: " + results.length);}
				if(results.length > 0) {
					if(results[0].split(":")[1].trim().equals("SUCCESS")) {
						// SUCCEEDED
						id = results[INFINIDATConstants.POS_VOLUME_ID].split(":")[1].trim();
						name = results[INFINIDATConstants.POS_VOLUME_NAME].split(":")[1].trim();
						type = results[INFINIDATConstants.POS_VOLUME_TYPE].split(":")[1].trim();
						size = results[INFINIDATConstants.POS_VOLUME_SIZE].split(":")[1].trim();
						serial = results[INFINIDATConstants.POS_VOLUME_SERIAL].split(":")[1].trim();
						code = results[INFINIDATConstants.POS_VOLUME_CODE].split(":")[1].trim();
					} else {
						// FAILED
						code = results[INFINIDATConstants.POS_ERROR_CODE].split(":")[1].trim();
						description = results[INFINIDATConstants.POS_ERROR_DESCRIPTION].split(":")[1].trim();
					}
				}
				
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_ID, id);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, name);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, type);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, size);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, serial);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, code);
	            context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, description);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - ID:  " + id);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - NAME:  " + name);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - TYPE:  " + type);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - SIZE:  " + size);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - SERIAL:  " + serial);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - CODE:  " + code);
	        	actionlogger.addInfo("Create Infinidat Volume task output set - DESCRIPTION:  " + description);
			}	
				
			if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- UPDATING KNOWN VOLUMES");}
        	func.refreshVolumes();
        	actionlogger.addInfo("Create Infinidat Volume task finsihed.");
        } catch (Exception e) {
            actionlogger.addWarning("Action " + INFINIDATcreateVolumeTaskConfig.displayLabel + ":" + e.getMessage());
            if(INFINIDATConstants.DEBUG_ERRORS){logger.info("----#### INFINIDATcreateVolumeTask:executeCustomAction ####---- ERROR: " + e.getMessage());}
        }
    }
    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
    	if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:getTaskOutputDefinitions ####----");}
   		TaskOutputDefinition[] ops = new TaskOutputDefinition[7];
   		ops[0] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_ID,  WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume ID");
   		ops[1] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Name");
   		ops[2] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Type");
   		ops[3] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Size");
   		ops[4] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Serial");
   		ops[5] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_CODE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "HTTP Code");
   		ops[6] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_ERROR_DESCRIPTION, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Error Description");
   		return ops;
    }
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:getTaskConfigImplementation ####----");}
    	return new INFINIDATcreateVolumeTaskConfig();
	}
	@Override
	public String getTaskName() {
		if(INFINIDATConstants.DEBUG_TASKS && INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATcreateVolumeTask:getTaskName ####----");}
    	return INFINIDATcreateVolumeTaskConfig.displayLabel;
	}
}