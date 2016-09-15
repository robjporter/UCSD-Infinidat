package com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;
import com.roporter.feature.json.myJson;

@SuppressWarnings("unused")
public class INFINIDATDisableFileSystemExportFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDisableFileSystemExportFormAction.class);
	private static final String formId = "infinidat.filesystem.export.disable.report.action.form.DisableFileSystem";
	private static final String ACTION_ID = "infinidat.filesystem.export.disable.report.form.action.DisableFileSystem";
	private static final String ACTION_LABEL = "Disable Filesystem Export";
	private static final String ACTION_DESCRIPTION = "Disable Filesystem Export";
	
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
		page.bind( formId, INFINIDATDisableFileSystemExportForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATDisableFileSystemExportForm form = new INFINIDATDisableFileSystemExportForm();
		form.setMessage("Disable the selected FileSystem Export with ID: " + id + "?");
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
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		List<String> toSend = new ArrayList<String>(); 
		toSend.add("\"enabled\": false");
		String send = myJson.formatJSON(toSend);
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- TO SEND: " + send );}
		
		String result = func.editExportByID(id,send).trim();
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- RESULT STATUS: " + results[0]);}
				if(results[INFINIDATConstants.POS_EXPORT_STATUS].split(":")[1].trim().equals("SUCCESS")) {
					String name = results[INFINIDATConstants.POS_EXPORT_PATH].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- NAME: " + name );}
					page.setPageMessage("FileSystem Export " + name + " has been delete Successfully.");
					func.refreshExports();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "FileSystem deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDisableFileSystemExportFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
