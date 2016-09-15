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
public class INFINIDATDeleteFileSystemExportPermissionFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeleteFileSystemExportPermissionFormAction.class);
	private static final String formId = "infinidat.filesystem.export.permission.delete.report.action.form.DeleteFileSystemPermission";
	private static final String ACTION_ID = "infinidat.filesystem.export.permission.deelte.report.form.action.DeleteFileSystemPermissions";
	private static final String ACTION_LABEL = "Delete Filesystem Export Permissions";
	private static final String ACTION_DESCRIPTION = "Delete Filesystem Export Permissions";
	
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
		page.bind( formId, INFINIDATDeleteFileSystemExportPermissionForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
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
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATDeleteFileSystemExportPermissionForm form = (INFINIDATDeleteFileSystemExportPermissionForm) obj;
		String permission = form.getTabularPopup();
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:validatePageData ####---- PERMISSION: " + permission);}

		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deleteExportPermission(id,permission).trim();
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteFileSystemExportPermissionFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteLunToHostFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[INFINIDATConstants.POS_EXPORT_CODE].trim().split(":")[1];
					page.setPageMessage("Permission has been successfully removed from Export.");
					func.refreshHosts();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToHostFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToHostFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToHostFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Export Permission removal failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToHostFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
