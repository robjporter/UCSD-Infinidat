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

public class INFINIDATAddNewClusterFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATAddNewClusterFormAction.class);
	private static final String formId = "infinidat.cluster.add.report.action.form.AddCluster";
	private static final String ACTION_ID = "infinidat.cluster.add.report.form.action.AddCluster";
	private static final String ACTION_LABEL = "Add Cluster";
	private static final String ACTION_DESCRIPTION = "Add new Cluster";
	
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
		return false;
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
		page.bind( formId, INFINIDATAddNewClusterForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction:loadDataToPage ####---- CONTEXT: " + context );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		INFINIDATAddNewClusterForm form = new INFINIDATAddNewClusterForm();
		form.setName("Cluster Name");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_STATIC_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATAddNewClusterForm form = (INFINIDATAddNewClusterForm) obj;
		String name = form.getName();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- NAME: " + name );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.createNewCluster(name).trim();
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- RESULT != NULL" );}
			String results[] = result.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String cid = results[1].trim().split(":")[1].trim();
					String code = results[2].trim().split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- ID: " + cid );}
					if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- CODE: " + code );}
					if(!id.isEmpty() && !code.isEmpty()) {
						if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- ID & CODE ARE SET!" );}
						page.setPageMessage("Cluster has been created Successfully with the ID: " + cid + ".");
						func.refreshClusters();
						page.setRefreshInSeconds(10);
						return PageIf.STATUS_OK;
					}			
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddHostToClusterFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- RESULT LENGTH != 2" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Volume creation failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewClusterFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
