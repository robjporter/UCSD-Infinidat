package com.cloupia.feature.infinidat.reports.device.hosts.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATHostMappedLuns;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATDeleteVolumeToHostForm {
    @FormField(label = "Select Lun", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATHostMappedLuns.HOST_MAPPED_LUNS_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
