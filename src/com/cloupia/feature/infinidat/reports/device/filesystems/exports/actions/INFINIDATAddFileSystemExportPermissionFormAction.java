package com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
public class INFINIDATAddFileSystemExportPermissionFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATAddFileSystemExportPermissionFormAction.class);
	private static final String formId = "infinidat.filesystem.export.permission.edit.report.action.form.EditFileSystem";
	private static final String ACTION_ID = "infinidat.filesystem.export.permission.edit.report.form.action.EditFileSystem";
	private static final String ACTION_LABEL = "Edit Filesystem Export Permissions";
	private static final String ACTION_DESCRIPTION = "Edit Filesystem Export Permissions";

	private String filesystemName = "";
	private int filesystemPool = 0;
	private String filesystemType = "Thin";
	private int filesystemSize = 0;
	private boolean filesystemSSD = true;
	
	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	@Override
	public String getFormId() {
		return formId;
	}
	@Override
	public String getLabel() {
		return ACTION_LABEL;
	}
	@Override
	public String getTitle() {
		return ACTION_LABEL;
	}
	@Override
	public String getDescription() {
		return ACTION_DESCRIPTION;
	}
	@Override
	public int getActionType() {
		return ConfigTableAction.ACTION_TYPE_POPUP_FORM;
	}
	@Override
	public boolean isSelectionRequired() {
		return true;
	}
	@Override
	public boolean isDoubleClickAction() {
		return false;
	}
	@Override
	public boolean isDrilldownAction() {
		return false;
	}
	@Override
	public void definePage(Page page, ReportContext context) {
		page.bind( formId, INFINIDATEditFileSystemExportForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		this.filesystemName = "FileSystem Name";
		this.filesystemPool = 0;
		this.filesystemType = "Thin";
		this.filesystemSize = 10;
		this.filesystemSSD = true;
		
		String values = func.getFileSystemInfoByID(id);
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- VALUES: " + values );}
		
		String value[] = values.split("\\|");
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- VALUE LENGTH : " + value.length );}
		
		if(value.length == 5) {
			this.filesystemName = value[0].trim();
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- NAME: " + this.filesystemName );}
			this.filesystemPool = myFormat.safeStringToInt(value[1].trim());
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- POOL: " + this.filesystemPool );}
			this.filesystemType = value[2].trim();
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- TYPE: " + this.filesystemType );}
			long tmp = myFormat.safeStringToLong(value[3].trim());
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- TMP: " + tmp );}
			this.filesystemSize = myFormat.humanReadableIntFromLong(tmp, true);
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- VSIZE: " + this.filesystemSize );}
			this.filesystemSSD = myFormat.safeStringToBoolean(value[4].trim());
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:loadDataToPage ####---- SSD: " + this.filesystemSSD );}
		}
		
		INFINIDATDeleteFileSystemExportPermissionForm form = new INFINIDATDeleteFileSystemExportPermissionForm();

		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_STATIC_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATDeleteFileSystemExportPermissionForm form = (INFINIDATDeleteFileSystemExportPermissionForm) obj;
		//String name = form.getName().trim();
		//if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- NAME: " + name + " | VOLUMENAME: " + this.filesystemName );}

		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result1 = "";
		String result2 = "";
		String result3 = "";
		String result4 = "";
		String result5 = "";
		
		//if(!this.filesystemName.equals(name)) {
		//	if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CHANGING NAME" );}
		//	result1 = func.editFileSystemByID(id, "name", name);
		//}

		
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT1: " + result1 );}
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT2: " + result2 );}
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT3: " + result3 );}
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT4: " + result4 );}
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT5: " + result5 );}
		
		int complete = 0;
		
		if(result1 != null && !result1.isEmpty()) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT1 != NULL" );}
			String results[] = result1.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 1");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT1 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT1 == NULL" );}
		}
		
		if(result2 != null && !result2.isEmpty()) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT2 != NULL" );}
			String results[] = result2.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 2");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 2 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 2 == NULL" );}
		}
		
		if(result3 != null && !result3.isEmpty()) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT3 != NULL" );}
			String results[] = result3.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].trim().split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 3");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 3 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 3 == NULL" );}
		}
		
		if(result4 != null && !result4.isEmpty()) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT4 != NULL" );}
			String results[] = result4.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 4");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 4 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 4 == NULL" );}
		}

		if(result5 != null && !result5.isEmpty()) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT5 != NULL" );}
			String results[] = result5.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 5");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 5 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 5 == NULL" );}
		}
		
		if(complete > 0) {
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- WE CHANGED SOMETHING ON THE FILESYSTEM" );}
			page.setPageMessage("FileSystem has been updated Successfully.");
			func.refreshFileSystems();
			page.setRefreshInSeconds(10);
			return PageIf.STATUS_OK;
		} else {
			page.setPageMessage( "FileSystem update failed!" );
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- ERROR!!!!" );}
			return PageIf.STATUS_ERROR;
		}
	}
}
