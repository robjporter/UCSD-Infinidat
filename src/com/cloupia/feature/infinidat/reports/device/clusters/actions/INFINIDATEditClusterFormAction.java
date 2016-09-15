package com.cloupia.feature.infinidat.reports.device.clusters.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class INFINIDATEditClusterFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATEditClusterFormAction.class);
	private static final String formId = "infinidat.cluster.edit.report.action.form.EditCluster";
	private static final String ACTION_ID = "infinidat.cluster.edit.report.form.action.EditCluster";
	private static final String ACTION_LABEL = "Edit Cluster";
	private static final String ACTION_DESCRIPTION = "Edit Cluster";
	
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
		page.bind( formId, INFINIDATEditClusterForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		INFINIDATEditClusterForm form = new INFINIDATEditClusterForm();
		form.setName(func.getClusterNameFromID(id));
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATEditClusterForm form = (INFINIDATEditClusterForm) obj;
		String name = form.getName();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- NAME: " + name );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.editClusterByID(id,name).trim();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- RESULT != NULL" );}
			String results[] = result.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditClusterFormAction:validatePageData ####---- SUCCESS" );}
					page.setPageMessage("Cluster has been successfully updated.");
					func.refreshClusters();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddHostToClusterFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Volume creation failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditClusterFormAction:loadDataToPage ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
