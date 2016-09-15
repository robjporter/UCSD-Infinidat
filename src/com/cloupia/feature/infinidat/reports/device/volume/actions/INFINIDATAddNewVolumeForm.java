package com.cloupia.feature.infinidat.reports.device.volume.actions;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.lovs.INFINIDATPoolProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATVolumeProvisionTypeProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATAddNewVolumeForm {
	@FormField(label = "Name", help = "Enter the name for the new Volume", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private String name;
	@FormField(label = "Pool", help = "Select the pool for the new volume", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATPoolProvisionTypeProvider.NAME)
	private int poolID;
	@FormField(label = "Provision Type", help = "Select the type of volume to provision", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = INFINIDATVolumeProvisionTypeProvider.NAME)
	private String provType;
	@FormField(label = "Size GB", help = "Enter the size of the new volume", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1, maxValue = INFINIDATConstants.INFINIDAT_MAX_VOLUME_SIZE, mandatory = true)
	private long size;
	@FormField(label = "SSD Cache", help = "Enable SSD Cache for new Volume", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
	private boolean ssd = true;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoolID() {
		return poolID;
	}
	public void setPoolID(int id) {
		this.poolID = id;
	}
	public String getProvType() {
		return this.provType;
	}
	public void setProvType(String provtype) {
		this.provType = provtype;
	}
	public long getSize() {
		return this.size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean getSSD() {
		return this.ssd;
	}
	public void setSSD(boolean ssd){
		this.ssd = ssd;
	}
}