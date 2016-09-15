package com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions;

import com.cloupia.feature.infinidat.lovs.INFINIDATExportPermissions;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATDeleteFileSystemExportPermissionForm {
    @FormField(label = "Select Permissions", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = INFINIDATExportPermissions.EXPORT_TABULAR_PROVIDER)
    private String tabularPopup;

	public String getTabularPopup() {
		return tabularPopup;
	}
	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}
}
