package com.cloupia.feature.infinidat.tasks;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;

@PersistenceCapable(detachable = "true", table = "infinidat_deletehostfromclustertaskconfig")
public class INFINIDATdeleteHostFromClusterTaskConfig implements TaskConfigIf {
	static Logger logger = Logger.getLogger(INFINIDATdeleteHostFromClusterTaskConfig.class);
	private String accountName;
	@Persistent
	private long configEntryId;
	@Persistent
	private long actionId;
	
	public static final String displayLabel = INFINIDATConstants.TASK_NAME_DELETE_HOST_CLUSTER; //"INFINIDATcreateVolumeTaskConfig";
	
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
        return INFINIDATConstants.TASK_NAME_DELETE_HOST_CLUSTER;
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
