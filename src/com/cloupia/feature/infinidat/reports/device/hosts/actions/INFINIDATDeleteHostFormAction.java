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

public class INFINIDATDeleteHostFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeleteHostFormAction.class);
	private static final String formId = "infinidat.host.delete.report.action.form.DeleteHost";
	private static final String ACTION_ID = "infinidat.host.delete.report.form.action.DeleteHost";
	private static final String ACTION_LABEL = "Delete Host";
	private static final String ACTION_DESCRIPTION = "Delete Host";
	
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
		page.bind( formId, INFINIDATDeleteHostForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATDeleteHostForm form = new INFINIDATDeleteHostForm();
		form.setMessage("Delete the selected Host with ID: " + id + "?");
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
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deleteHost(id);
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String name = results[0].split(":")[1].trim();
					String vid = results[1].split(":")[1].trim();
					String luns = results[4].split(":")[1].trim();
					String ports = results[5].split(":")[1].trim();
					String code = results[6].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- NAME: " + name );}
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- ID: " + vid );}
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- LUNS: " + luns );}
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- PORTS: " + ports );}
					if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- CODE: " + code );}
					page.setPageMessage("Host " + name + " with id: " + vid + " has been deleted successfully.  It had " + luns + " Luns and " + ports + " ports associated." );
					func.refreshHosts();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Host deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteHostFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}			