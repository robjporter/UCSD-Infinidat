package com.cloupia.feature.infinidat.reports.device.filesystems.actions;

import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATExportFileSystemForm {
	@FormField(label = "Name", help = "Enter the name for the FileSystem Export", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}