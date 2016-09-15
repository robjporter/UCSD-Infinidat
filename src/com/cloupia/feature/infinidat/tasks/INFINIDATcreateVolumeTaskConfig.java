package com.cloupia.feature.infinidat.tasks;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.lovs.INFINIDATAccountsNameProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATPoolProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATVolumeProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.INFINDATVolumeSizeUnitProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

@PersistenceCapable(detachable = "true", table = "infinidat_createvolumetaskconfig")
public class INFINIDATcreateVolumeTaskConfig implements TaskConfigIf {
	static Logger logger = Logger.getLogger(INFINIDATcreateVolumeTaskConfig.class);
    @FormField(label = "Infinidat Account", help = "Infinidat Account", mandatory=true, type=FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATAccountsNameProvider.NAME)
    @UserInputField(type = INFINIDATConstants.INFINIDAT_ACCOUNT_LOV_NAME)
    private String accountName;
    @FormField(label = "Volume Name", help = "Letters, numbers, -, and _", mandatory = true )
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
    @Persistent
    private String volumePreName;
    @FormField(label = "Volume Size Unit", help = "FlashArray Volume Size Unit", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINDATVolumeSizeUnitProvider.NAME)
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
    @Persistent
    private String volumeSizeUnit;
    @FormField(label = "Volume Size Number", help = "Numbers", mandatory = true)
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
    @Persistent
    private String volumeSizeNumber;	
    @FormField(label = "Provising Pool", help = "Pool to provision new Volume in", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATPoolProvisionTypeProvider.NAME)
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
    @Persistent
    private String poolProvisionID;
    @FormField(label = "Volume Provision Type", help = "Type of new volume, either Thick or Thin provisioned", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATVolumeProvisionTypeProvider.NAME)
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
    @Persistent
    private String volumeProvisionType;
    
    
    @Persistent
	private long configEntryId;
	@Persistent
	private long actionId;

	public static final String displayLabel = INFINIDATConstants.TASK_NAME_NEW_VOLUME; //"INFINIDATcreateVolumeTaskConfig";
	
	public String getPoolProvisionID() {
		return this.poolProvisionID;
	}
	public void setPoolProvisionID(String id) {
		this.poolProvisionID = id;
	}
	public String getVolumeProvisionType() {
		return this.volumeProvisionType;
	}
	public void setVolumeProvisionType(String type) {
		this.volumeProvisionType = type;
	}
	public String getVolumeName() {
		return this.volumePreName;
	}
	public void setVolumeName(String name) {
		this.volumePreName = name;
	}
	public String getVolumeSizeUnit() {
		return this.volumeSizeUnit;
	}
	public void setVolumeSizeUnit(String unit) {
		this.volumeSizeUnit = unit;
	}
	public String getVolumeSize() {
		return this.volumeSizeNumber;
	}
	public void setVolumeSize(String size) {
		this.volumeSizeNumber = size;
	}
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

	@Override
	public long getActionId() {
		return actionId;
	}

	@Override
	public long getConfigEntryId() {
		return configEntryId;
	}
	
	@Override
    public String getDisplayLabel() {
        return INFINIDATConstants.TASK_NAME_NEW_VOLUME;
    }

	@Override
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Override
	public void setConfigEntryId(long configEntryId) {
		this.configEntryId = configEntryId;
	}
}
