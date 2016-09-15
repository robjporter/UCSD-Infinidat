package com.cloupia.feature.infinidat.reports.device.pools.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class INFINIDATEditPoolFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATEditPoolFormAction.class);
	private static final String formId = "infinidat.pool.edit.report.action.form.EditPool";
	private static final String ACTION_ID = "infinidat.pool.edit.report.form.action.EditPool";
	private static final String ACTION_LABEL = "Edit";
	private static final String ACTION_DESCRIPTION = "Edit Pool";
	
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
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeletePoolFormAction:definePage ####---- ID: " + context );}
		page.bind(formId, INFINIDATEditPoolForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeletePoolFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditPoolFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditPoolFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}

		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		INFINIDATEditPoolForm form = new INFINIDATEditPoolForm();
		form.setName(func.getPoolNameFromID(id));
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditPoolFormAction:validatePagedata ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATEditPoolForm form = (INFINIDATEditPoolForm) obj;
		String name = form.getName();
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- NAME: " + name );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.editPoolByID(id,name).trim();
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].trim().split(":")[1];
					page.setPageMessage("Pool has been successfully updated.");
					func.refreshPools();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Pool Update failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditPoolFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
