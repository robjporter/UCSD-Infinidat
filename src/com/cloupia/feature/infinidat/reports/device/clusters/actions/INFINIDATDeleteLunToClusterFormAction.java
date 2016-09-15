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

public class INFINIDATDeleteLunToClusterFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeleteLunToClusterFormAction.class);
	private static final String formId = "infinidat.cluster.delete.lun.report.action.form.DeleteVolumeCluster";
	private static final String ACTION_ID = "infinidat.cluster.delete.lun.report.form.action.DeleteVolumeCluster";
	private static final String ACTION_LABEL = "Delete Volume from Cluster";
	private static final String ACTION_DESCRIPTION = "Delete Volume from Cluster";
	
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
		page.bind( formId, INFINIDATDeleteLunToClusterForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		INFINIDATDeleteLunToClusterForm form = new INFINIDATDeleteLunToClusterForm();
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATDeleteLunToClusterForm form = (INFINIDATDeleteLunToClusterForm) obj;
		String volumeid = form.getTabularPopup();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- VOLUME ID: " + volumeid );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deleteLunToCluster(id,volumeid).trim();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- RESULT != NULL" );}
			String results[] = result.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					page.setPageMessage("Host has been mapped successfully to Cluster.");
					func.refreshClusters();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Host delete from Cluster failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteLunToClusterFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
