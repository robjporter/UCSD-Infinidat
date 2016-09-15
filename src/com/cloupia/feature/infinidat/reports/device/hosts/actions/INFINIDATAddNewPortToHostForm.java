package com.cloupia.feature.infinidat.reports.device.hosts.actions;

import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATAddNewPortToHostForm {
	@FormField(label = "WWPN", help = "Enter the WWPN for the new port", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.NETWORKING_DEVICE_MACADDRESS)
	private String wwpn;

	public String getWWPN() {
		return wwpn;
	}
	public void setWWPN(String wwpn) {
		this.wwpn = wwpn;
	}
}
