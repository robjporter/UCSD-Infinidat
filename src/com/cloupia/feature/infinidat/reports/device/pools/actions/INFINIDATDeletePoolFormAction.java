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
import com.roporter.feature.format.myFormat;

public class INFINIDATDeletePoolFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeletePoolFormAction.class);
	private static final String formId = "infinidat.pool.delete.report.action.form.DeletePool";
	private static final String ACTION_ID = "infinidat.pool.delete.report.form.action.DeletePool";
	private static final String ACTION_LABEL = "Delete";
	private static final String ACTION_DESCRIPTION = "Delete Pool";
	
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
		page.bind(formId,INFINIDATDeletePoolForm.class);
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
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePoolFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePoolFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATDeletePoolForm form = new INFINIDATDeletePoolForm();
		form.setMessage("Delete the selected Pool?");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deletePool(id);
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- LENGTH: " + results.length );}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String pid = results[1].split(":")[1].trim();
					String name = results[2].split(":")[1].trim();
					String vcount = results[3].split(":")[1].trim();
					String fcount = results[7].split(":")[1].trim();
					String pcapacity = results[16].split(":")[1].trim();
					String vcapacity = results[17].split(":")[1].trim();
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- NAME: " + name );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- ID: " + pid );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- VCOUNT: " + vcount );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- FCOUNT: " + fcount );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- VCAPACITY: " + vcapacity );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- PCAPACITY: " + pcapacity );}
					if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- CODE: " + code );}
					page.setPageMessage("Pool " + name + " with ID: " + pid + " has been deleted successfully.  Removing " + vcount + " volumes & " + fcount + " Filesystems.  Returning " + myFormat.humanReadableFromString(pcapacity, true) + " space." );
					func.refreshPools();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1]);
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;
				}
			}  else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Pool deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeletePoolFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
