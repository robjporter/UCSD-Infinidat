package com.cloupia.feature.infinidat.reports.device.volume.actions;

import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATDeleteVolumeForm {
	@FormField(label = "Attention: ", help = "Warning message.", mandatory = true, editable = false)
 	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}