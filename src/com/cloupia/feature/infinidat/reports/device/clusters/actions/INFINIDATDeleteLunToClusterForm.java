package com.cloupia.feature.infinidat.reports.device.clusters.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATClusteredLunsLOV;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATDeleteLunToClusterForm {
    @FormField(label = "Select Lun", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATClusteredLunsLOV.HOSTS_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
