package com.cloupia.feature.infinidat.tasks;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.lovs.INFINIDATAccountsNameProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATVolumeInventoryProvisionTypeProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

@PersistenceCapable(detachable = "true", table = "infinidat_deletevolumetaskconfig")
public class INFINIDATdeleteVolumeByNameTaskConfig implements TaskConfigIf {
	static Logger logger = Logger.getLogger(INFINIDATdeleteVolumeByNameTaskConfig.class);
    @FormField(label = "Infinidat Account", help = "Infinidat Account", mandatory=true, type=FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATAccountsNameProvider.NAME)
    @UserInputField(type = INFINIDATConstants.INFINIDAT_ACCOUNT_LOV_NAME)
    private String accountName;
    @FormField(label = "Volume ID", help = "Volume ID to delete", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATVolumeInventoryProvisionTypeProvider.NAME)
    @UserInputField(type = INFINIDATConstants.INFINIDAT_VOLUME_INVENTORY_PROVISION_TYPE_LOV_NAME)
    @Persistent
    private String volumeID;
	@Persistent
	private long configEntryId;
	@Persistent
	private long actionId;

	public static final String displayLabel = INFINIDATConstants.TASK_NAME_DELETE_NAME_VOLUME; //"INFINIDATcreateVolumeTaskConfig";
	

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getVolumeID() {
    	return this.volumeID;
    }
    public void setVolumeID(String id) {
    	this.volumeID = id;
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
        return INFINIDATConstants.TASK_NAME_DELETE_NAME_VOLUME;
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
