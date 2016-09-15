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

public class INFINIDATDeletePortToHostFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeletePortToHostFormAction.class);
	private static final String formId = "infinidat.host.new.report.action.form.DeleteHostPort";
	private static final String ACTION_ID = "infinidat.host.new.report.form.action.DeleteHostPort";
	private static final String ACTION_LABEL = "Delete Host Port";
	private static final String ACTION_DESCRIPTION = "Delete Host Port";
	
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
		page.bind( formId, INFINIDATDeletePortToHostForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePortToHostFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePortToHostFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATDeletePortToHostForm form = new INFINIDATDeletePortToHostForm();
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATDeletePortToHostForm form = (INFINIDATDeletePortToHostForm) obj;
		String port = form.getTabularPopup();
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- PORT: " + port );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deleteHostPort(id,port).trim();
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[1].trim().split(":")[1];
					page.setPageMessage("Host Port has been deleted Successfully.");
					func.refreshHosts();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Host Port deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePortToHostFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
