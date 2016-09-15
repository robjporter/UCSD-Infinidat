package com.cloupia.feature.infinidat.reports.device.pools.actions;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FieldValidation;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;
import com.cloupia.service.cIM.inframgr.forms.wizard.HideFieldOnCondition;

public class INFINIDATAddNewPoolForm {
	@FormField(label = "Name", help = "Enter the name for the new Pool", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private String name;
	
	@FormField(label = "Physical Size GB", help = "Enter the size of the new Pool - Min 1000", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = INFINIDATConstants.INFINIDAT_MIN_POOL_SIZE, maxValue = INFINIDATConstants.INFINIDAT_MAX_PHYSICAL_POOL_SIZE, mandatory = true)
	private int pSize;
	
    @FormField(label = "Link Physical/Virtual Capacity", help = "", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN, validate = true)
    @UserInputField(type = WorkflowInputFieldTypeDeclaration.BOOLEAN)
    private boolean link = true;
    
	@FormField(label = "Virtual Size GB", help = "Enter the size of the new Pool - Min 1000", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1, maxValue = INFINIDATConstants.INFINIDAT_MAX_VIRTUAL_POOL_SIZE, mandatory = true)
	@HideFieldOnCondition(field = "link", op = FieldValidation.OP_EQUALS, value = "true")
	@UserInputField(type =  WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int vSize;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhysicalSize() {
		return this.pSize;
	}
	public void setPhysicalSize(int size) {
		this.pSize = size;
	}
	public boolean getLinkStatus() {
		return this.link;
	}
	public void setLinkStatus(boolean status) {
		this.link = status;
	}
	public int getVirtualSize() {
		return this.vSize;
	}
	public void setVirtualSize(int size) {
		this.vSize = size;
	}
}
