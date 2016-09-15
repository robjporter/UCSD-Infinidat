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

public class INFINIDATDeleteClusterFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeleteClusterFormAction.class);
	private static final String formId = "infinidat.cluster.delete.report.action.form.DeleteCluster";
	private static final String ACTION_ID = "infinidat.cluster.delete.report.form.action.DeleteCluster";
	private static final String ACTION_LABEL = "Delete Cluster";
	private static final String ACTION_DESCRIPTION = "Delete Cluster";
	
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
		page.bind( formId, INFINIDATDeleteClusterForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddHostToClusterFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddHostToClusterFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddHostToClusterFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		INFINIDATDeleteClusterForm form = new INFINIDATDeleteClusterForm();
		form.setMessage("Delete the selected Cluster with ID: " + id + "?");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddHostToClusterFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		String result = func.deleteCluster(id);
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- RESULT != NULL" );}
			String results[] = result.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String name = results[1].split(":")[1].trim();
					String vid = results[2].split(":")[1].trim();
					String luns = results[3].split(":")[1].trim();
					String hosts = results[4].split(":")[1].trim();
					String code = results[5].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- NAME: " + name );}
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- ID: " + vid );}
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- LUNS: " + luns );}
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- PORTS: " + hosts );}
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- CODE: " + code );}
					
					if(name != "" && vid != "") {
						page.setPageMessage("Cluster " + name + " with id: " + vid + " has been deleted successfully.  It had " + luns + " Luns and " + hosts + " associated." );
						func.refreshClusters();
						page.setRefreshInSeconds(10);
						return PageIf.STATUS_OK;
					}
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddHostToClusterFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			}
		} else {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Cluster deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteClusterFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
