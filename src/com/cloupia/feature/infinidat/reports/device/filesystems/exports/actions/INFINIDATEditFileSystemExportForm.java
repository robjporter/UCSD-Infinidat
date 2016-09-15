package com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions;

import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class INFINIDATEditFileSystemExportForm {
	@FormField(label = "Name", help = "Enter the name for the FileSystem", mandatory = true, editable = false)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private String name;
	@FormField(label = "Anonymous UID", help = "Select the Anonymous UID for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int anonymousUID;
	@FormField(label = "Anonymous GID", help = "Select the Anonymous GID for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int anonymousGID;
    @FormField(label = "Squash users and Groups", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
    private boolean squash;
    @FormField(label = "Privilege ports only", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
    private boolean ports;
	@FormField(label = "Max Read Size", help = "Select the Max read size for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int maxread;
	@FormField(label = "Max Write Size", help = "Select the Max write size for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int maxwrite;
	@FormField(label = "Preferred Read Size", help = "Select the preferred read size for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int prefreadsize;
	@FormField(label = "Preferred Write Size", help = "Select the preferred write size for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int prefwritesize;
	@FormField(label = "Preferred Read Dir Size", help = "Select the preferred read dir size for the Export", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.GENERIC_TEXT)
	private int prefreaddir;
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnonymousUID() {
		return this.anonymousUID;
	}
	public void setAnonymousUID(int id) {
		this.anonymousUID = id;
	}
	public int getAnonymousGID() {
		return this.anonymousGID;
	}
	public void setAnonymousGID(int id) {
		this.anonymousGID = id;
	}
	public boolean getSquash() {
		return this.squash;
	}
	public void setSquash(boolean squash) {
		this.squash = squash;
	}
	public boolean getPorts() {
		return this.ports;
	}
	public void setPorts(boolean ports) {
		this.ports = ports;
	}
	public int getMaxRead() {
		return this.maxread;
	}
	public void setMaxRead(int maxread) {
		this.maxread = maxread;
	}
	public int getMaxWrite() {
		return this.maxwrite;
	}
	public void setMaxWrite(int maxwrite) {
		this.maxwrite = maxwrite;
	}
	public int getPrefReadSize() {
		return this.prefreadsize;
	}
	public void setPrefReadSize(int prefreadsize) {
		this.prefreadsize = prefreadsize;
	}
	public int getPrefWriteSize() {
		return this.prefwritesize;
	}
	public void setPrefWriteSize(int prefwritesize) {
		this.prefwritesize = prefwritesize;
	}
	public int getPrefReadDir() {
		return this.prefreaddir;
	}
	public void setPrefReadDir(int prefreaddir) {
		this.prefreaddir = prefreaddir;
	}
}
