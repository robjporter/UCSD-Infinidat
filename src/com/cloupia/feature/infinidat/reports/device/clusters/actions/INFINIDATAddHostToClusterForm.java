package com.cloupia.feature.infinidat.reports.device.clusters.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATHostsNotConnectedLOV;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATAddHostToClusterForm {
    @FormField(label = "Select Host", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATHostsNotConnectedLOV.HOSTS_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
