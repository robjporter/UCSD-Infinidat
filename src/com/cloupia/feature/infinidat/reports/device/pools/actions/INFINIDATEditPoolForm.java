package com.cloupia.feature.infinidat.reports.device.pools.actions;

import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATEditPoolForm {
	@FormField(label = "Name", help = "Enter the new name for the Pool", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
