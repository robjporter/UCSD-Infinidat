package com.cloupia.feature.infinidat.reports.device.snapshots.actions;

import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATEditVolumeSnapshotForm {
	@FormField(label = "Name", help = "Enter the name for the new Volume", mandatory = true)
	private String name;

	@FormField(label = "SSD Cache", help = "Enable SSD Cache for this Volume", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
	private boolean ssd = true;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSSD() {
		return this.ssd;
	}
	public void setSSD(boolean ssd){
		this.ssd = ssd;
	}
}
