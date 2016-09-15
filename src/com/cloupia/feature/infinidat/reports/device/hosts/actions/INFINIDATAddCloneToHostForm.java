package com.cloupia.feature.infinidat.reports.device.hosts.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATHostClonesLOV;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATAddCloneToHostForm {
    @FormField(label = "Select Clone", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATHostClonesLOV.HOSTS_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
