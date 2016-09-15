package com.cloupia.feature.infinidat.reports.device.clusters.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATClusterLunsLOV;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATAddLunToClusterForm {
    @FormField(label = "Select Lun", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATClusterLunsLOV.HOSTS_TABULAR_PROVIDER)
    private String tabularPopup;
	@FormField(label = "Lun Number", help = "Enter the number for the new Lun", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1, maxValue = 1000, mandatory = true)
	private int number;

	public int getLunID() {
		return number;
	}
	public void setLunID(int number) {
		this.number = number;
	}
	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
