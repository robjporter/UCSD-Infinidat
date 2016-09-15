package com.cloupia.feature.infinidat.reports.device.hosts.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class INFINIDATAddCloneToHostFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATAddCloneToHostFormAction.class);
	private static final String formId = "infinidat.host.add.clone.report.action.form.AddLunToHost";
	private static final String ACTION_ID = "infinidat.host.add.clone.report.form.action.AddLunToHost";
	private static final String ACTION_LABEL = "Add Clone to Host";
	private static final String ACTION_DESCRIPTION = "Add Clone to Host";
	
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
		page.bind( formId, INFINIDATAddCloneToHostForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATAddCloneToHostForm form = new INFINIDATAddCloneToHostForm();
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATAddCloneToHostForm form = (INFINIDATAddCloneToHostForm) obj;
		String lun = form.getTabularPopup();
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- LUN: " + lun );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.AddLunToHost(id,lun).trim();
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- SUCCESS" );}
					page.setPageMessage("Clone has been mapped successfully to Host.");
					func.refreshHosts();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Clone mapping to Host failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddCloneToHostFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}