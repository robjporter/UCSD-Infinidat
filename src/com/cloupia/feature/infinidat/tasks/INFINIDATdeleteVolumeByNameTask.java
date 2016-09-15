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

public class INFINIDATdeleteVolumeByNameTask extends AbstractTask {
	static Logger logger = Logger.getLogger(INFINIDATdeleteVolumeByNameTask.class);
	
    @Override
    public void executeCustomAction(CustomActionTriggerContext context, CustomActionLogger actionlogger) throws Exception {
    	logger.info( "----#### INFINIDATdeleteVolumeTask:executeCustomAction ####---- CONTEXT: " + context );
    	actionlogger.addInfo("Delete Infinidat Volume task started.");
		long configEntryId = context.getConfigEntry().getConfigEntryId();
		INFINIDATdeleteVolumeByNameTaskConfig config = (INFINIDATdeleteVolumeByNameTaskConfig) context.loadConfigObject();
		if (config == null) { throw new Exception("No Group config found for custom action " + context.getActionDef().getName() + " entryId " + configEntryId); }
    	actionlogger.addInfo("Delete Infinidat Volume task config loaded.");
    	
		actionlogger.addInfo("Delete Infinidat task type is: " + this.getTaskType());
		//		context.getChangeTracker().undoableResourceAdded("assetType", "idString", "Hello World", "Hello World task", new RollbackHelloWorldTask().getTaskName(), new RollbackHelloWorldConfig(config));
		try {
			INFINIDATFunctions funcs = new INFINIDATFunctions(INFINIDATFunctions.getDeviceCredentials(config.getAccountName()));
			String result = funcs.deleteVolume(config.getVolumeID());
	    	actionlogger.addInfo("Delete Infinidat Volume task response from Infinidat array.");
	    	if( result != "" && result != null ) {
		    	String[] splits = result.split( " | " );
		    	
		    	if( splits.length == 5 ) {
			        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, splits[0].split(":")[1]);
			        actionlogger.addInfo("Create Infinidat Volume task output set - Name:  " + splits[0].split(":")[1]);
			        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, splits[1].split(":")[1]);
			        actionlogger.addInfo("Create Infinidat Volume task output set - Type:  " + splits[1].split(":")[1]);
			        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, splits[2].split(":")[1]);
			        actionlogger.addInfo("Create Infinidat Volume task output set - Size:  " + splits[2].split(":")[1]);
			        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, splits[3].split(":")[1]);
			        actionlogger.addInfo("Create Infinidat Volume task output set - Serial:  " + splits[3].split(":")[1]);
			        context.saveOutputValue(INFINIDATConstants.TASK_OUTPUT_VOLUME_USED, splits[4].split(":")[1]);
			        actionlogger.addInfo("Create Infinidat Volume task output set - Used:  " + splits[4].split(":")[1]);
		    	} else {
					logger.info("----#### INFINIDATdeleteVolumeTask:executeCustomAction ####---- INVALID SIZE FROM RESULT - SIZE: " + splits.length );
		    	}
	    	}
			logger.info("----#### INFINIDATdeleteVolumeTask:executeCustomAction ####---- UPDATING KNOWN VOLUMES");
        	funcs.refreshVolumes();
        } catch (Exception e) {
            actionlogger.addWarning("Action " + INFINIDATdeleteVolumeByNameTaskConfig.displayLabel + ":" + e.getMessage());
			logger.info("----#### INFINIDATdeleteVolumeTask:executeCustomAction ####---- ERROR: " + e.getMessage());
        }
    }
    
    @Override
   	public TaskOutputDefinition[] getTaskOutputDefinitions() {
   		TaskOutputDefinition[] ops = new TaskOutputDefinition[5];
   		ops[0] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_NAME, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Name");
   		ops[1] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_TYPE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Type");
   		ops[2] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SIZE, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Size");
   		ops[3] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_SERIAL, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Serial");
   		ops[4] = new TaskOutputDefinition(INFINIDATConstants.TASK_OUTPUT_VOLUME_USED, WorkflowInputFieldTypeDeclaration.GENERIC_TEXT, "Volume Used");
   		return ops;
    }
	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		return new INFINIDATdeleteVolumeByNameTaskConfig();
	}
	@Override
	public String getTaskName() {
		return INFINIDATdeleteVolumeByNameTaskConfig.displayLabel;
	}
}