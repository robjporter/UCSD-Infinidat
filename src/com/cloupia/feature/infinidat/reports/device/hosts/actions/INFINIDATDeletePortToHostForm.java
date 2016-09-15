package com.cloupia.feature.infinidat.reports.device.hosts.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATHostPorts;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATDeletePortToHostForm {
    @FormField(label = "Select Port", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATHostPorts.HOST_PORT_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}